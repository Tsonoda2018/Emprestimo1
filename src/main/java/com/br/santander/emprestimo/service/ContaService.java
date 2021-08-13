package com.br.santander.emprestimo.service;

import java.util.List;

import com.br.santander.emprestimo.model.Conta;

public interface ContaService {
	Conta salvar(Conta conta);

	List<Conta> buscarTodos();

	Conta buscarPorId(Integer id);
	
	void excluir(Integer id);
}
