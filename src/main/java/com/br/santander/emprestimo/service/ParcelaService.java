package com.br.santander.emprestimo.service;

import java.util.List;

import com.br.santander.emprestimo.model.Parcela;

public interface ParcelaService {
	Parcela salvar(Parcela parcela);

	List<Parcela> buscarTodos();

	Parcela buscarPorId(Integer id);
	
	void excluir(Integer id);
}
