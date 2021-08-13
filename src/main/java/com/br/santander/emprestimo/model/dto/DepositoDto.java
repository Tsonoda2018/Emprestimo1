package com.br.santander.emprestimo.model.dto;

import java.math.BigDecimal;

public class DepositoDto {

	private Integer idCliente;
	private Integer idConta;
	private BigDecimal valor;

	public DepositoDto(Integer idCliente, Integer idConta, BigDecimal valor) {
		super();
		this.idCliente = idCliente;
		this.idConta = idConta;
		this.valor = valor;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public Integer getIdConta() {
		return idConta;
	}

	public BigDecimal getValor() {
		return valor;
	}

}
