package com.br.santander.emprestimo.model.dto;

public class FormLoginDto {

	private String email;
	private String senha;

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}

	public FormLoginDto(String email, String senha) {
		super();
		this.email = email;
		this.senha = senha;
	}

	@Override
	public String toString() {
		return "FormLoginDto [email=" + email + ", senha=" + senha + "]";
	}

}
