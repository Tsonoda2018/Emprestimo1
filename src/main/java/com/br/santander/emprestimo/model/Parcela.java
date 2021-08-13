package com.br.santander.emprestimo.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Parcela {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private BigDecimal valor;
	private Integer numero;
	private LocalDate dataParcela;
	private StatusParcela status;
	@ManyToOne
	private Proposta proposta;

	public Parcela(BigDecimal valor, Integer numero, LocalDate dataParcela, Proposta proposta) {
		this.valor = valor;
		this.numero = numero;
		this.dataParcela = dataParcela;
		this.proposta = proposta;
		this.status = StatusParcela.AGENDADA;
	}

	public Parcela() {
	}

	public Integer getId() {
		return id;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public Integer getNumero() {
		return numero;
	}

	public LocalDate getDataParcela() {
		return dataParcela;
	}

	public StatusParcela getStatus() {
		return status;
	}

	public Proposta getProposta() {
		return proposta;
	}

}
