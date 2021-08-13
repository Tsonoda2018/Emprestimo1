package com.br.santander.emprestimo.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.br.santander.emprestimo.model.Cliente;
import com.br.santander.emprestimo.model.Conta;
import com.br.santander.emprestimo.model.dto.AberturaContaDto;
import com.br.santander.emprestimo.model.dto.AberturaContaInputDto;
import com.br.santander.emprestimo.model.dto.ClienteInputDto;
import com.br.santander.emprestimo.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	private final ClienteService clienteService;

	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody ClienteInputDto clienteInputDto, UriComponentsBuilder uriBuilder) {
		Cliente cliente = clienteInputDto.converte();
		Cliente clienteSalvo = clienteService.salvar(cliente);
		URI uri = uriBuilder.path("/clientes/{id}").buildAndExpand(clienteSalvo.getId()).toUri();
		return ResponseEntity.created(uri).body(clienteInputDto);
	}

	@PostMapping("/contas")
	public ResponseEntity<?> abrirConta(@RequestBody AberturaContaInputDto aberturaContaInputDto, UriComponentsBuilder uriBuilder) {
		Conta conta = clienteService.abrirConta(aberturaContaInputDto.getIdCliente(), aberturaContaInputDto.getNumero(), aberturaContaInputDto.getAgencia());
		URI uri = uriBuilder.path("/clientes/conta/{id}").buildAndExpand(conta.getId()).toUri();
		AberturaContaDto contaDto = AberturaContaDto.converte(conta);
		return ResponseEntity.created(uri).body(contaDto);
	} 

}
