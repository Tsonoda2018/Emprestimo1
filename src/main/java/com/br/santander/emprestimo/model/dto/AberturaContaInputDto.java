package com.br.santander.emprestimo.model.dto;

public class AberturaContaInputDto {
	private Integer idCliente;
	private String numero;
	private Integer agencia;

	public AberturaContaInputDto(Integer idCliente, String numero, Integer agencia) {
		this.idCliente = idCliente;
		this.numero = numero;
		this.agencia = agencia;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public String getNumero() {
		return numero;
	}

	public Integer getAgencia() {
		return agencia;
	}

}
