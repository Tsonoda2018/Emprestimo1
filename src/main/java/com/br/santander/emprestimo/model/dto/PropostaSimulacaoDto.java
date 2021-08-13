package com.br.santander.emprestimo.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.hateoas.RepresentationModel;

public class PropostaSimulacaoDto extends RepresentationModel<PropostaSimulacaoDto> {
	private String nomeCliente;
	private LocalDate dataSimulacao;
	private Integer quantidadeParcelas;
	private BigDecimal valorParcela;
	private Double taxaJuros;

	public PropostaSimulacaoDto(String nomeCliente, LocalDate dataSimulacao, Integer quantidadeParcelas,
			BigDecimal valorParcela, Double taxaJuros) {
		super();
		this.nomeCliente = nomeCliente;
		this.dataSimulacao = dataSimulacao;
		this.quantidadeParcelas = quantidadeParcelas;
		this.valorParcela = valorParcela;
		this.taxaJuros = taxaJuros;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public LocalDate getDataSimulacao() {
		return dataSimulacao;
	}

	public Integer getQuantidadeParcelas() {
		return quantidadeParcelas;
	}

	public BigDecimal getValorParcela() {
		return valorParcela;
	}

	public Double getTaxaJuros() {
		return taxaJuros;
	}


}
