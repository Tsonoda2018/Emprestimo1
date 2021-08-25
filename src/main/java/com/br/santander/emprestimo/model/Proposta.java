package com.br.santander.emprestimo.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.br.santander.emprestimo.model.dto.PropostaSimulacaoDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Proposta {

	private static Double TAXA_JUROS = 0.0117;
	private static BigDecimal UM = new BigDecimal(1);
	private static BigDecimal PERCENTUAL_MAX_PRESTACAO = new BigDecimal(0.15);
	private static BigDecimal PATRIMONIO_MIN = new BigDecimal(300000);
	private static long QUANTIDADE_MESES_MIN = 15L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private BigDecimal valor;
	private Double taxaJuros;
	private Integer quantidadeParcelas;
	@JsonIgnore
	@ManyToOne
	private Cliente cliente;
	private LocalDate dataContratacao;
	private StatusProposta status;

	@OneToMany(mappedBy = "proposta", cascade = CascadeType.REMOVE)
	private List<Parcela> parcelas = new ArrayList<>();

	public Proposta(BigDecimal valor, Integer quantidadeParcelas, Cliente cliente) {
		this.valor = valor;
		this.taxaJuros = TAXA_JUROS;
		this.quantidadeParcelas = quantidadeParcelas;
		this.cliente = cliente;
		this.dataContratacao = LocalDate.now();
		this.status = StatusProposta.ANALISE;
	}

	public Proposta() {
	}

	public BigDecimal getValor() {
		return valor;
	}

	public Double getTaxaJuros() {
		return taxaJuros;
	}

	public Integer getQuantidadeParcelas() {
		return quantidadeParcelas;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public LocalDate getDataContratacao() {
		return dataContratacao;
	}

	public StatusProposta getStatus() {
		return status;
	}

	public Integer getId() {
		return id;
	}

	public List<Parcela> getParcelas() {
		return Collections.unmodifiableList(parcelas);
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	private BigDecimal calcularPrestacao() {
		BigDecimal valor = new BigDecimal(Math.pow((1 + TAXA_JUROS), this.quantidadeParcelas));
		BigDecimal valor2 = valor.multiply(new BigDecimal(TAXA_JUROS)).divide(valor.subtract(UM), 5,
				RoundingMode.HALF_UP);
		BigDecimal PMT = this.valor.multiply(valor2);

		return PMT;
	}

	public boolean validarEnquadramentoProposta() throws Exception {
		if (this.cliente.calcularTempoServico() < QUANTIDADE_MESES_MIN) {
			throw new Exception("proposta não aprovada, tempo de serviço " + this.cliente.calcularTempoServico()
					+ " menor que " + QUANTIDADE_MESES_MIN);
		}

		if (this.cliente.getPatrimonio().compareTo(PATRIMONIO_MIN) < 0) {
			throw new Exception("proposta não aprovada, patrimonio " + this.cliente.getPatrimonio() + " menor que "
					+ PATRIMONIO_MIN);
		}

		if (cliente.getSalario().multiply(PERCENTUAL_MAX_PRESTACAO).compareTo(calcularPrestacao()) < 0) {
			throw new Exception("proposta não aprovada, valor maximo de parcela:  "
					+ cliente.getSalario().multiply(PERCENTUAL_MAX_PRESTACAO).doubleValue() + " Parcela calculada: "
					+ calcularPrestacao().doubleValue());
		}

		return true;
	}

	public PropostaSimulacaoDto simular() {
		try {
			if (!this.validarEnquadramentoProposta()) {
				throw new RuntimeException("Proposta não enquadrada");

			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return new PropostaSimulacaoDto(this.getCliente().getNome(), LocalDate.now(), quantidadeParcelas,
				this.calcularPrestacao(), taxaJuros);

	}

	public void addParcela(Parcela parcela) {
		parcelas.add(parcela);
	}

	public void setStatus(StatusProposta status) {
		this.status = status;
	}

}
