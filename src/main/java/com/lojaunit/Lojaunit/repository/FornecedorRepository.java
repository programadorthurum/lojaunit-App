package com.lojaunit.Lojaunit.repository;

import org.springframework.data.repository.CrudRepository;

import com.lojaunit.Lojaunit.model.Fornecedor;

public interface FornecedorRepository extends CrudRepository<Fornecedor, Integer> {

	// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
	// CRUD refers Create, Read, Update, Delete

}