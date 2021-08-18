package com.br.santander.emprestimo.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.net.URI;

import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
		Link propostas = linkTo(PropostaController.class).withRel("propostas").withType("POST");
		return ResponseEntity.ok().body(propostaSimulada.add(propostas));
	}

	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody PropostaInputDto propostaInputDto, UriComponentsBuilder uriBuilder) {
		Proposta proposta = propostaInputDto.converte();
		proposta.setCliente(clienteService.buscarPorId(propostaInputDto.getIdCliente()));
		Proposta propostaSalva = propostaService.salvar(proposta);
		URI uri = uriBuilder.path("/propostas/{id}").buildAndExpand(propostaSalva.getId()).toUri();
		return ResponseEntity.created(uri).body(propostaSalva);
	}

}
