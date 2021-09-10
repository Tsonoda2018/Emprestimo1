package com.br.santander.emprestimo.service.impl;

import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.br.santander.emprestimo.model.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	private String secret = "Vn1QS52PhsI51^FuxPh@Mg2$7q#VQ@yZnGSVe75QOuj@";
	private long expiration = 1800000;

	public String geraToken(Authentication authentication) {
		Usuario usuarioLogado = (Usuario) authentication.getPrincipal();
		Date hoje = new Date();
		Date exp = new Date(hoje.getTime() + expiration);

		return Jwts.builder().setSubject(usuarioLogado.getId().toString()).setIssuedAt(hoje).setExpiration(exp)
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	public boolean validaToken(String token) {

		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Integer getUsuarioId(String token) {
		Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		return Integer.parseInt(claims.getSubject());
	}
}
