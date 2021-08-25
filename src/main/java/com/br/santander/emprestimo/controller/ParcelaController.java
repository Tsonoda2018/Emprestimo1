package com.br.santander.emprestimo.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.net.URI;

import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.br.santander.emprestimo.model.Parcela;
import com.br.santander.emprestimo.model.dto.PropostaInputDto;
import com.br.santander.emprestimo.model.dto.PropostaSimulacaoDto;
import com.br.santander.emprestimo.service.ClienteService;
import com.br.santander.emprestimo.service.ParcelaService;

@RestController
@RequestMapping("/parcelas")
public class ParcelaController {

	private final ParcelaService parcelaService;

	public ParcelaController(ParcelaService parcelaService) {
		super();
		this.parcelaService = parcelaService;
	}

	@PutMapping("/pagar")
	public ResponseEntity<?> pagarParcela(@RequestParam Integer idParcela, @RequestParam Integer idConta) {
		Parcela parcela = parcelaService.buscarPorId(idParcela);
//		parcelaService.liberar(parcela, idConta);
		return ResponseEntity.ok(parcela);
	}

}
