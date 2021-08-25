package com.br.santander.emprestimo.service;

import com.br.santander.emprestimo.model.Proposta;

public interface PagamentoService {
	void liberarValor(Proposta proposta, Integer idConta);
	void pagarParcela(Integer idParcela, Integer idConta);
}
