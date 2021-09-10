package com.br.santander.emprestimo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.santander.emprestimo.model.dto.FormLoginDto;
import com.br.santander.emprestimo.model.dto.TokenDto;
import com.br.santander.emprestimo.service.impl.TokenService;

@RestController
@RequestMapping("auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	TokenService tokenService;

	@PostMapping
	public ResponseEntity<?> autentica(@RequestBody FormLoginDto formLogin) {
		Authentication authenticate = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(formLogin.getEmail(), formLogin.getSenha()));

		String token = tokenService.geraToken(authenticate);
		System.out.println(formLogin);
		return ResponseEntity.ok(new TokenDto(token, "Bearer"));
	}
}
