package com.br.santander.emprestimo.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ClienteFiltroDto {

	private String nome;
	private BigDecimal salario;
	private LocalDate dataContratacao;

	public ClienteFiltroDto(String nome, BigDecimal salario, LocalDate dataContratacao) {
		super();
		this.nome = nome;
		this.salario = salario;
		this.dataContratacao = dataContratacao;
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public LocalDate getDataContratacao() {
		return dataContratacao;
	}

}
