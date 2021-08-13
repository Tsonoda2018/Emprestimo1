package com.br.santander.emprestimo.service.impl;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.br.santander.emprestimo.model.Cliente;
import com.br.santander.emprestimo.model.Conta;
import com.br.santander.emprestimo.repository.ClienteRepository;
import com.br.santander.emprestimo.service.ClienteService;
import com.br.santander.emprestimo.service.ContaService;

@Service
public class ClienteServiceImpl implements ClienteService {

	private final ClienteRepository clienteRepository;
	private final ContaService contaService;

	public ClienteServiceImpl(ClienteRepository clienteRepository, ContaService contaService) {
		this.clienteRepository = clienteRepository;
		this.contaService = contaService;
	}

	@Override
	public Cliente salvar(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	@Override
	public List<Cliente> buscarTodos() {
		return clienteRepository.findAll();
	}

	@Override
	public void excluir(Integer id) {
		clienteRepository.delete(this.buscarPorId(id));
	}

	@Override
	public Cliente buscarPorId(Integer id) {
		return clienteRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Não existe cliente com o id " + id));
	}

	@Override
	public Conta abrirConta(Integer idCliente, String numero, Integer agencia) {
		Cliente cliente = this.buscarPorId(idCliente);
		Conta conta = new Conta(cliente, numero, agencia);
		return contaService.salvar(conta);
	}

}
