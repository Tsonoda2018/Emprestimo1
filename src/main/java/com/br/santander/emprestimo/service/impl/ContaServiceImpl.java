package com.br.santander.emprestimo.service.impl;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.br.santander.emprestimo.model.Conta;
import com.br.santander.emprestimo.repository.ContaRepository;
import com.br.santander.emprestimo.service.ContaService;
@Service
public class ContaServiceImpl implements ContaService {
	private final ContaRepository contaRepository;

	public ContaServiceImpl(ContaRepository contaRepository) {
		this.contaRepository = contaRepository;
	}

	@Override
	public Conta salvar(Conta conta) {
		return contaRepository.save(conta);
	}

	@Override
	public List<Conta> buscarTodos() {
		return contaRepository.findAll();
	}

	@Override
	public Conta buscarPorId(Integer id) {
		return contaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Não existe conta com o id " + id));
	}

	@Override
	public void excluir(Integer id) {
		contaRepository.delete(this.buscarPorId(id));
	}

}
