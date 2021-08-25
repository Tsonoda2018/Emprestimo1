package com.br.santander.emprestimo.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Conta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	private Cliente cliente;
	private BigDecimal saldo;
	private String numero;
	private Integer agencia;

	public Conta(Cliente cliente, String numero, Integer agencia) {
		this.cliente = cliente;
		this.numero = numero;
		this.agencia = agencia;
		this.saldo = new BigDecimal(0);
	}

	public Conta() {
	}

	public Integer getId() {
		return id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public String getNumero() {
		return numero;
	}

	public Integer getAgencia() {
		return agencia;
	}

	public void depositar(BigDecimal valor) {
		this.saldo = this.saldo.add(valor);
	}

	public void sacar(BigDecimal valor) {
		if (this.saldo.compareTo(valor) < 0) {
			throw new RuntimeException("Saldo insuficiente.");
		}
		this.saldo = this.saldo.min(valor);
	}

}
