package com.br.santander.emprestimo.model.dto;

import com.br.santander.emprestimo.model.Conta;

public class AberturaContaDto {
	private String nomeCliente;
	private String numero;
	private Integer agencia;

	public AberturaContaDto(String nomeCliente, String numero, Integer agencia) {
		this.nomeCliente = nomeCliente;
		this.numero = numero;
		this.agencia = agencia;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public String getNumero() {
		return numero;
	}

	public Integer getAgencia() {
		return agencia;
	}

	public static AberturaContaDto converte(Conta conta) {
		return new AberturaContaDto(conta.getCliente().getNome(), conta.getNumero(), conta.getAgencia());

	}

}
