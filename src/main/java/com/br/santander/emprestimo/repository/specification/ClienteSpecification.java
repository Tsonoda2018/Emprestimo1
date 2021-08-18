package com.br.santander.emprestimo.repository.specification;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;

import com.br.santander.emprestimo.model.Cliente;

public class ClienteSpecification {

	public static Specification<Cliente> porNomeCliente(String nomeCliente) {
		return (root, query, builder) -> nomeCliente != null ? builder.like(root.get("nome"), "%" + nomeCliente + "%")
				: null;
	}

	public static Specification<Cliente> porSalario(BigDecimal salario) {
		return (root, query, builder) -> salario != null ? builder.greaterThanOrEqualTo(root.get("salario"), salario)
				: null;
	}
	public static Specification<Cliente> porDataContratacao(LocalDate data) {
		return (root, query, builder) -> data != null ? builder.greaterThanOrEqualTo(root.get("dataContratacao"), data)
				: null;
	}
}
