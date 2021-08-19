package com.br.santander.emprestimo.service;

import java.util.List;

import com.br.santander.emprestimo.model.Proposta;

public interface PropostaService {
	Proposta salvar(Proposta proposta);

	List<Proposta> buscarTodos();

	Proposta buscarPorId(Integer id);
	
	void excluir(Integer id);

	void liberar(Proposta proposta, Integer idConta);
}
