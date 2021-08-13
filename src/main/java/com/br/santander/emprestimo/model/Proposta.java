package com.br.santander.emprestimo.model;

import java.math.BigDecimal;
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

@Entity
public class Proposta {
	
	private static Double TAXA_JUROS = 1.17;
	private static BigDecimal UM = new BigDecimal(1);
	private static BigDecimal PERCENTUAL_MAX_PRESTACAO = new BigDecimal(15);
	private static BigDecimal PATRIMONIO_MIN = new BigDecimal(100000);
	private static long QUANTIDADE_MESES_MIN = 15L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private BigDecimal valor;
	private Double taxaJuros;
	private Integer quantidadeParcelas;
	@ManyToOne
	private Cliente cliente;
	private LocalDate dataContratacao;
	private StatusProposta status;

	@OneToMany(mappedBy = "proposta", cascade = CascadeType.ALL)
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

	private BigDecimal calcularPrestacao() {
		BigDecimal valor = new BigDecimal(Math.pow((1 + TAXA_JUROS), this.quantidadeParcelas));
		BigDecimal PMT = this.valor.multiply(valor.multiply(new BigDecimal(TAXA_JUROS)).divide(valor.subtract(UM)));

		return PMT;
	}
	
	public boolean validarEnquadramentoProposta( ) {
		if (this.cliente.calcularTempoServico() < QUANTIDADE_MESES_MIN) {
			return false;
		}
		
		if (this.cliente.getPatrimonio().compareTo(PATRIMONIO_MIN) < 0 ) {
			return false;
		}
		
		if (cliente.getSalario().multiply(PERCENTUAL_MAX_PRESTACAO).compareTo(calcularPrestacao()) < 0 ) {
			return false;
		}
		
		return true;
	}

}
