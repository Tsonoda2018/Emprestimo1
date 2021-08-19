package com.br.santander.emprestimo.service.impl;

import org.springframework.stereotype.Service;

import com.br.santander.emprestimo.model.Conta;
import com.br.santander.emprestimo.model.Proposta;
import com.br.santander.emprestimo.service.ClienteService;
import com.br.santander.emprestimo.service.ContaService;
import com.br.santander.emprestimo.service.PagamentoService;
import com.br.santander.emprestimo.service.ParcelaService;

@Service
public class PagamentoServiceImpl implements PagamentoService {

	private final ClienteService clienteService;
	private final ParcelaService parcelaService;
	private final ContaService contaService;

	public PagamentoServiceImpl(ClienteService clienteService, ParcelaService parcelaService,
			ContaService contaService) {
		super();
		this.clienteService = clienteService;
		this.parcelaService = parcelaService;
		this.contaService = contaService;
	}

	@Override
	public void liberarValor(Proposta proposta, Integer idConta) {
		Conta conta = contaService.buscarPorId(idConta);

		conta.depositar(proposta.getValor());
		contaService.salvar(conta);

	}

}
