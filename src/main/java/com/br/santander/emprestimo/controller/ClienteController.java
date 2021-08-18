package com.br.santander.emprestimo.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.br.santander.emprestimo.model.Cliente;
import com.br.santander.emprestimo.model.Conta;
import com.br.santander.emprestimo.model.dto.AberturaContaDto;
import com.br.santander.emprestimo.model.dto.AberturaContaInputDto;
import com.br.santander.emprestimo.model.dto.ClienteDto;
import com.br.santander.emprestimo.model.dto.ClienteFiltroDto;
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
	public ResponseEntity<?> abrirConta(@RequestBody AberturaContaInputDto aberturaContaInputDto,
			UriComponentsBuilder uriBuilder) {
		Conta conta = clienteService.abrirConta(aberturaContaInputDto.getIdCliente(), aberturaContaInputDto.getNumero(),
				aberturaContaInputDto.getAgencia());
		URI uri = uriBuilder.path("/clientes/conta/{id}").buildAndExpand(conta.getId()).toUri();
		AberturaContaDto contaDto = AberturaContaDto.converte(conta);
		return ResponseEntity.created(uri).body(contaDto);
	}

	@GetMapping
	public ResponseEntity<List<ClienteDto>> buscarTodos(ClienteFiltroDto filtro) {
		List<ClienteDto> dtos = clienteService.buscarTodos(filtro).stream().map(m -> {
			ClienteDto dto = ClienteDto.converte(m);
			Link self = linkTo(ClienteController.class).slash(m.getId()).withSelfRel();
			Link clientes = linkTo(ClienteController.class).withRel("clientes");
			dto.add(self).add(clientes);
			return dto;
		}).collect(Collectors.toList());
		return ResponseEntity.ok(dtos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
		Cliente cliente = clienteService.buscarPorId(id);
		ClienteDto clienteDto = ClienteDto.converte(cliente);
		Link self = linkTo(ClienteController.class).slash(cliente.getId()).withSelfRel();
		Link clientes = linkTo(ClienteController.class).withRel("clientes");
		clienteDto.add(self).add(clientes);
		return  ResponseEntity.ok(clienteDto);
	}
}
