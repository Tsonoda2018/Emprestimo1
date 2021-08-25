package com.br.santander.emprestimo.service.impl;

import org.springframework.stereotype.Service;

import com.br.santander.emprestimo.model.Conta;
import com.br.santander.emprestimo.model.Parcela;
import com.br.santander.emprestimo.model.Proposta;
import com.br.santander.emprestimo.model.StatusParcela;
import com.br.santander.emprestimo.model.StatusProposta;
import com.br.santander.emprestimo.service.ClienteService;
import com.br.santander.emprestimo.service.ContaService;
import com.br.santander.emprestimo.service.PagamentoService;
import com.br.santander.emprestimo.service.ParcelaService;

@Service
public class PagamentoServiceImpl implements PagamentoService {

	private final ParcelaService parcelaService;
	private final ContaService contaService;

	public PagamentoServiceImpl(ParcelaService parcelaService, ContaService contaService) {
		super();
		this.parcelaService = parcelaService;
		this.contaService = contaService;
	}

	@Override
	public void liberarValor(Proposta proposta, Integer idConta) {
		if (proposta.getStatus().equals(StatusProposta.APROVADA)) {
			Conta conta = contaService.buscarPorId(idConta);
			proposta.setStatus(StatusProposta.LIBERADA);
			conta.depositar(proposta.getValor());
			contaService.salvar(conta);
		} else {
			throw new RuntimeException("Não foi possível liberar status da proposta: " + proposta.getStatus());
		}

	}

	@Override
	public void pagarParcela(Integer idParcela, Integer idConta) {
		Parcela parcela = parcelaService.buscarPorId(idParcela);
		if (parcela.getStatus().equals(StatusParcela.AGENDADA) || parcela.getStatus().equals(StatusParcela.VENCIDA)) {
			Conta conta = contaService.buscarPorId(idConta);
			conta.sacar(parcela.getValor());

		} else {
			throw new RuntimeException("Não foi possível pagar a parcela, pois o status é: " + parcela.getStatus());
		}

	}

}
