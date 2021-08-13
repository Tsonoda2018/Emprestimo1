package com.br.santander.emprestimo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.br.santander.emprestimo.model.Conta;

public interface ContaRepository extends JpaRepository<Conta, Integer>, JpaSpecificationExecutor<Conta> {

}
