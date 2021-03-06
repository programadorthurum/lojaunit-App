package com.lojaunit.Lojaunit.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lojaunit.Lojaunit.model.Categoria;
import com.lojaunit.Lojaunit.repository.CategoriaRepository;

@Controller
@RequestMapping(path = "/categoria")
public class CategoriaController {
	@Autowired // This means to get the bean called userRepository
	// Which is auto-generated by Spring, we will use it to handle the data

	private CategoriaRepository categoriaRepository;

	@PostMapping(path = "/add") // Map ONLY POST Requests
	public @ResponseBody String addNewCategoria(@RequestParam String nome, @RequestParam boolean ativo) {

		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

		Categoria categoria = new Categoria();
		categoria.setNome(nome);
		categoria.setAtivo(ativo);

		categoriaRepository.save(categoria);
		return "Categoria cadastrada";
	}

	/**
	 * Altera o id e o ativo
	 * 
	 * @param nome
	 * @param ativo
	 * @param id
	 * @return
	 */
	@PutMapping(path = "/alterar/{id}")
	public @ResponseBody String alterarCategoria(@RequestParam String nome, @RequestParam boolean ativo,
			@PathVariable("id") Integer id) {

		if (categoriaRepository.existsById(id)) {
			Categoria categoria = new Categoria();
			categoria.setId(id);
			categoria.setNome(nome);
			categoria.setAtivo(ativo);

			categoriaRepository.save(categoria);
			return "Categoria atualizada com Sucesso!";
		}
		return "Categoria não encontrada!";
	}

	/**
	 * Altera apenas o nome id
	 * 
	 * @param nome
	 * @param id
	 * @return
	 */
	@PutMapping(path = "/alterar_nome{id}")
	public @ResponseBody String alterarCategoria(@RequestParam String nome, @PathVariable("id") Integer id) {

		if (categoriaRepository.existsById(id)) {
			Categoria categoria = new Categoria();
			categoria.setId(id);
			categoria.setNome(nome);

			categoriaRepository.save(categoria);
			return "Categoria atualizada com Sucesso!";
		}
		return "Categoria não encontrada!";
	}

	@GetMapping(path = "/consultar")
	public @ResponseBody Optional<Categoria> consultarCategoria(@RequestParam Integer id) {

		return categoriaRepository.findById(id);
	}

	@DeleteMapping(path = "/remover")
	public @ResponseBody String deletarCategoria(@RequestParam Integer id) {

		if (categoriaRepository.existsById(id)) {
			categoriaRepository.deleteById(id);
			return "Cliente removido!";
		}
		return "Cliente não encontrado para exclusão!";
	}

	@GetMapping(path = "/listar")
	public @ResponseBody Iterable<Categoria> getAllCategoria() {
		return categoriaRepository.findAll();
	}

}