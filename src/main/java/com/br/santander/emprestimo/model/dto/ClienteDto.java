package com.br.santander.emprestimo.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.hateoas.RepresentationModel;

import com.br.santander.emprestimo.model.Cliente;

public class ClienteDto extends RepresentationModel<ClienteDto> {

	private String nome;
	private BigDecimal salario;
	private LocalDate dataContratacao;
	private BigDecimal patrimonio;
	private String cpf;

	public ClienteDto(String nome, BigDecimal salario, LocalDate dataContratacao, BigDecimal patrimonio, String cpf) {
		super();
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

	public static ClienteDto converte(Cliente cliente) {
		return new ClienteDto(cliente.getNome(), cliente.getSalario(), cliente.getDataContratacao(), cliente.getPatrimonio(), cliente.getCpf());
	}

}
