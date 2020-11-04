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
import com.lojaunit.Lojaunit.model.Fornecedor;
import com.lojaunit.Lojaunit.model.Marca;
import com.lojaunit.Lojaunit.model.Produto;
import com.lojaunit.Lojaunit.repository.CategoriaRepository;
import com.lojaunit.Lojaunit.repository.ProdutoRepository;
import com.lojaunit.Lojaunit.repository.FornecedorRepository;
import com.lojaunit.Lojaunit.repository.MarcaRepository;


@Controller
@RequestMapping(path = "/produto") 
public class ProdutoController {
	@Autowired 
	private ProdutoRepository Produtorepository;
	@Autowired 
	private MarcaRepository Marcarepository;
	@Autowired 
	private FornecedorRepository Fornecedorrepository;
	@Autowired 
	private CategoriaRepository categoriaRepository;


	@PostMapping(path = "/add") // Map ONLY POST Requests
	public @ResponseBody String addNewProduto(@RequestParam String nome, @RequestParam String descricao,
			@RequestParam Double preco_unitario, @RequestParam Integer unidade, @RequestParam Integer idCategoria, @RequestParam Integer idFornecedor, 
			@RequestParam Integer idMarca) {

		Produto prod = new Produto();
		prod.setNome(nome);
		prod.setDescricao(descricao);
		prod.setPreco_unitario(preco_unitario);
		prod.setUnidade(unidade);
		
		Categoria categoria= categoriaRepository.findById(idCategoria).get();
		prod.setCategoria(categoria);
		
		Marca marca= Marcarepository.findById(idMarca).get();
		prod.setMarca(marca);
		
		Fornecedor fornecedor= Fornecedorrepository.findById(idFornecedor).get();
		prod.setFornecedor(fornecedor);
		
		Produtorepository.save(prod);
		return "Produto Salvo com sucesso!!!";
	}


	@PutMapping(path = "/alterar/{id}")	
	public @ResponseBody String alterarProduto(@RequestParam String nome, @RequestParam String descricao,
			@RequestParam Double preco_unitario, @RequestParam Integer unidade, @RequestParam Integer idCategoria, @RequestParam Integer idFornecedor, @RequestParam Integer idMarca,
												@PathVariable("id") Integer id) {

		if (Produtorepository.existsById(id)) {	
			Produto prod = new Produto();			
			prod.setId(id);			
			prod.setNome(nome);
			prod.setDescricao(descricao);
			prod.setPreco_unitario(preco_unitario);
			prod.setUnidade(unidade);
			
			Categoria categoria= categoriaRepository.findById(idCategoria).get();
			prod.setCategoria(categoria);
			
			Marca marca= Marcarepository.findById(idMarca).get();
			prod.setMarca(marca);
			
			Fornecedor fornecedor= Fornecedorrepository.findById(idFornecedor).get();
			prod.setFornecedor(fornecedor);
			
			Produtorepository.save(prod);
			return "Produto atualizado com Sucesso!";
			}
		return "Produto não encontrado!";
	}

	
	@GetMapping(path = "/consultar")
	public @ResponseBody Optional<Produto> consultarProdutos(@RequestParam Integer id){

			return Produtorepository.findById(id);
	}
	
	@DeleteMapping(path = "/remover")
	public @ResponseBody String deletarProduto(@RequestParam Integer id) {
	
		if (Produtorepository.existsById(id)) {
			Produtorepository.deleteById(id);
		return "Produto removido!";
		}
		return "Produto não encontrado para exclusão!";
	}

	@GetMapping(path = "/listar")
	public @ResponseBody Iterable<Produto> getAllProduto() {
		return Produtorepository.findAll();
	}

}