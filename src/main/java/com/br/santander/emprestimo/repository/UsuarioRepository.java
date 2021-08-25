package com.br.santander.emprestimo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.santander.emprestimo.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	Optional<Usuario> findByEmail(String username);

}
