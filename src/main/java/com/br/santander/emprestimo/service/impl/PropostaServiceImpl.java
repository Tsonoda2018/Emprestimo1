package com.br.santander.emprestimo.service.impl;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.br.santander.emprestimo.model.Proposta;
import com.br.santander.emprestimo.repository.PropostaRepository;
import com.br.santander.emprestimo.service.PropostaService;
@Service
public class PropostaServiceImpl implements PropostaService {

	private final PropostaRepository propostaRepository;

	public PropostaServiceImpl(PropostaRepository propostaRepository) {
		this.propostaRepository = propostaRepository;
	}

	@Override
	public Proposta salvar(Proposta proposta) {
		if (proposta.validarEnquadramentoProposta()) {
			return propostaRepository.save(proposta);
		}
		
		throw new RuntimeException("Proposta não enquadrada");
	}

	@Override
	public List<Proposta> buscarTodos() {
		return propostaRepository.findAll();
	}

	@Override
	public Proposta buscarPorId(Integer id) {
		return propostaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Não existe proposta com o id " + id));
	}

	@Override
	public void excluir(Integer id) {
		propostaRepository.delete(this.buscarPorId(id));
	}

}
