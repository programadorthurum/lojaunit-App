package com.lojaunit.Lojaunit.repository;

import org.springframework.data.repository.CrudRepository;

import com.lojaunit.Lojaunit.model.Produto;

public interface ProdutoRepository extends CrudRepository<Produto, Integer> {

	// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
	// CRUD refers Create, Read, Update, Delete

}