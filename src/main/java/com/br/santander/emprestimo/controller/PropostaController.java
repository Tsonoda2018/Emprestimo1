package com.br.santander.emprestimo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.br.santander.emprestimo.model.Proposta;
import com.br.santander.emprestimo.model.dto.PropostaInputDto;
import com.br.santander.emprestimo.model.dto.PropostaSimulacaoDto;
import com.br.santander.emprestimo.service.ClienteService;
import com.br.santander.emprestimo.service.PropostaService;

@RestController
@RequestMapping("/propostas")
public class PropostaController {

	private final PropostaService propostaService;
	private final ClienteService clienteService;

	public PropostaController(PropostaService propostaService, ClienteService clienteService) {
		this.propostaService = propostaService;
		this.clienteService = clienteService;
	}

	@GetMapping("/simular")
	public ResponseEntity<?> simular(@RequestBody PropostaInputDto propostaInputDto, UriComponentsBuilder uriBuilder) {
		Proposta proposta = propostaInputDto.converte();
		proposta.setCliente(clienteService.buscarPorId(propostaInputDto.getIdCliente()));
		PropostaSimulacaoDto propostaSimulada = proposta.simular();
		return ResponseEntity.ok().body(propostaSimulada);
	}

}
