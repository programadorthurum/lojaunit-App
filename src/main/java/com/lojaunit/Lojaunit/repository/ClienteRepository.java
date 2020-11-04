package com.lojaunit.Lojaunit.repository;

import org.springframework.data.repository.CrudRepository;

import com.lojaunit.Lojaunit.model.Clientes;

public interface ClienteRepository extends CrudRepository<Clientes, Integer> {

	// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
	// CRUD refers Create, Read, Update, Delete

}


