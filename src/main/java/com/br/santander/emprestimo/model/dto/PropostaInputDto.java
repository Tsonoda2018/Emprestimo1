package com.br.santander.emprestimo.model.dto;

import java.math.BigDecimal;

import com.br.santander.emprestimo.model.Proposta;

public class PropostaInputDto {
	private BigDecimal valor;
	private Integer quantidadeParcelas;
	private Integer idCliente;

	public PropostaInputDto(BigDecimal valor, Integer quantidadeParcelas, Integer idCliente) {
		super();
		this.valor = valor;
		this.quantidadeParcelas = quantidadeParcelas;
		this.idCliente = idCliente;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public Integer getQuantidadeParcelas() {
		return quantidadeParcelas;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public Proposta converte() {
		return new Proposta(valor, quantidadeParcelas, null);
		
	}

}
