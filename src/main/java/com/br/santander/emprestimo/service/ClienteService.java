package com.br.santander.emprestimo.service;

import java.util.List;

import com.br.santander.emprestimo.model.Cliente;
import com.br.santander.emprestimo.model.Conta;
import com.br.santander.emprestimo.model.dto.ClienteFiltroDto;

public interface ClienteService {
	Cliente salvar(Cliente cliente);

	List<Cliente> buscarTodos(ClienteFiltroDto filtro);

	Cliente buscarPorId(Integer id);
	
	void excluir(Integer id); 
	
	Conta abrirConta(Integer idCliente, String numero, Integer agencia);
}
