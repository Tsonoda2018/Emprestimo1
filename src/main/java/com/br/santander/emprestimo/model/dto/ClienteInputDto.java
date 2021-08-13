package com.br.santander.emprestimo.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.br.santander.emprestimo.model.Cliente;

public class ClienteInputDto {
	private String nome;
	private BigDecimal salario;
	private LocalDate dataContratacao;
	private BigDecimal patrimonio;
	private String cpf;

	public ClienteInputDto(String nome, BigDecimal salario, LocalDate dataContratacao, BigDecimal patrimonio,
			String cpf) {
		this.nome = nome;
		this.salario = salario;
		this.dataContratacao = dataContratacao;
		this.patrimonio = patrimonio;
		this.cpf = cpf;
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

	public BigDecimal getPatrimonio() {
		return patrimonio;
	}

	public String getCpf() {
		return cpf;
	}

	public Cliente converte() {
		return new Cliente(nome, salario, dataContratacao, patrimonio, cpf);
	}

}
