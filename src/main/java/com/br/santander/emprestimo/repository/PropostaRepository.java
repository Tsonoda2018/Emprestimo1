package com.br.santander.emprestimo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.br.santander.emprestimo.model.Proposta;

public interface PropostaRepository extends JpaRepository<Proposta, Integer>, JpaSpecificationExecutor<Proposta> {

}
