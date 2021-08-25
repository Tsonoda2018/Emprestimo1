package com.br.santander.emprestimo.service.impl;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.br.santander.emprestimo.model.Parcela;
import com.br.santander.emprestimo.repository.ParcelaRepository;
import com.br.santander.emprestimo.service.ParcelaService;
@Service	
public class ParcelaServiceImpl implements ParcelaService {
	
	private final ParcelaRepository parcelaRepository;

	public ParcelaServiceImpl(ParcelaRepository parcelaRepository) {
		this.parcelaRepository = parcelaRepository;
	}

	@Override
	public Parcela salvar(Parcela parcela) {
		return parcelaRepository.save(parcela);
	}

	@Override
	public List<Parcela> buscarTodos() {
		return parcelaRepository.findAll();
	}


	@Override
	public void excluir(Integer id) {
		parcelaRepository.delete(this.buscarPorId(id));
	}

	@Override
	public Parcela buscarPorId(Integer id) {
		return parcelaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Não existe parcela com o id " + id));
	}

	@Override
	public void salvarTodos(List<Parcela> parcelas) {
		parcelaRepository.saveAll(parcelas);
		
	}

}
