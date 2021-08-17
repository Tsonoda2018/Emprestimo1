package com.br.santander.emprestimo.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private BigDecimal salario;
	private LocalDate dataContratacao;
	private BigDecimal patrimonio;
	private String cpf;

	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<Proposta> propostas = new ArrayList<>();
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<Conta> contas = new ArrayList<>();

	public Cliente(String nome, BigDecimal salario, LocalDate dataContratacao, BigDecimal patrimonio, String cpf) {
		super();
		this.nome = nome;
		this.salario = salario;
		this.dataContratacao = dataContratacao;
		this.patrimonio = patrimonio;
		this.cpf = cpf;
	}
	
	public Cliente() {
	}

	public Integer getId() {
		return id;
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

	public List<Proposta> getPropostas() {
		return Collections.unmodifiableList(propostas);
	}

	public List<Conta> getContas() {
		return Collections.unmodifiableList(contas);
	}
	
	public long calcularTempoServico() {
		long diferencaEmMes = ChronoUnit.MONTHS.between( dataContratacao, LocalDate.now());
		return diferencaEmMes;
	}

}
