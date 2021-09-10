package com.br.santander.emprestimo.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.br.santander.emprestimo.model.Usuario;
import com.br.santander.emprestimo.service.impl.TokenService;

public class JwtTokenFilter extends OncePerRequestFilter {

	private TokenService tokenService;

	private AutenticacaoService autenticacaoService;

	public JwtTokenFilter(TokenService tokenService, AutenticacaoService autenticacaoService) {
		this.tokenService = tokenService;
		this.autenticacaoService = autenticacaoService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = getToken(request);
		if (tokenService.validaToken(token)) {
			autenticaUsuario(token);
		}
		filterChain.doFilter(request, response);
	}

	private void autenticaUsuario(String token) {
		Integer idUsuario = tokenService.getUsuarioId(token);
		Usuario usuario = autenticacaoService.buscarPorId(idUsuario);
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null,
				usuario.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private String getToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}

		return token.substring(7);
	}

}
