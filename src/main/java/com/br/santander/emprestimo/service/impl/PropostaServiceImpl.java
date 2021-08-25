package com.br.santander.emprestimo.service.impl;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.br.santander.emprestimo.model.Parcela;
import com.br.santander.emprestimo.model.Proposta;
import com.br.santander.emprestimo.model.StatusProposta;
import com.br.santander.emprestimo.model.dto.PropostaSimulacaoDto;
import com.br.santander.emprestimo.repository.PropostaRepository;
import com.br.santander.emprestimo.service.PagamentoService;
import com.br.santander.emprestimo.service.ParcelaService;
import com.br.santander.emprestimo.service.PropostaService;

@Service
public class PropostaServiceImpl implements PropostaService {

	private final PropostaRepository propostaRepository;
	private final ParcelaService parcelaService;
	private final PagamentoService pagamentoService;

	public PropostaServiceImpl(PropostaRepository propostaRepository, ParcelaService parcelaService,
			PagamentoService pagamentoService) {
		this.propostaRepository = propostaRepository;
		this.parcelaService = parcelaService;
		this.pagamentoService = pagamentoService;
	}

	@Override
	public Proposta salvar(Proposta proposta) {
		try {
			if (proposta.validarEnquadramentoProposta()) {
				PropostaSimulacaoDto simular = proposta.simular();
				for (int i = 1; i <= proposta.getQuantidadeParcelas(); i++) {
					Parcela parcela = new Parcela(simular.getValorParcela(), i, proposta);
					proposta.addParcela(parcela);

				}
				proposta.setStatus(StatusProposta.APROVADA);
				Proposta propostaSalva = propostaRepository.save(proposta);
//				proposta.getParcelas().forEach(p -> parcelaService.salvar(p));
				parcelaService.salvarTodos(proposta.getParcelas());
				return propostaSalva;
			}
		} catch (Exception e) {
			proposta.setStatus(StatusProposta.REPROVADA);
			propostaRepository.save(proposta);
			throw new RuntimeException(e.getMessage());
		}
		return proposta;

	}

	@Override
	public List<Proposta> buscarTodos() {
		return propostaRepository.findAll();
	}

	@Override
	public Proposta buscarPorId(Integer id) {
		return propostaRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Não existe proposta com o id " + id));
	}

	@Override
	public void excluir(Integer id) {
		propostaRepository.delete(this.buscarPorId(id));
	}

	@Override
	public void liberar(Proposta proposta, Integer idConta) {
		pagamentoService.liberarValor(proposta, idConta);

	}

}
