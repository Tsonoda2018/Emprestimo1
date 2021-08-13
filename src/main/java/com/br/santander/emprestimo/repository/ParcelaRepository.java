package com.br.santander.emprestimo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.br.santander.emprestimo.model.Parcela;

public interface ParcelaRepository extends JpaRepository<Parcela, Integer>, JpaSpecificationExecutor<Parcela> {

}
