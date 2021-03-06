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

import com.lojaunit.Lojaunit.model.ItensVenda;
import com.lojaunit.Lojaunit.model.Produto;
import com.lojaunit.Lojaunit.model.Venda;
import com.lojaunit.Lojaunit.repository.ItensVendaRepository;
import com.lojaunit.Lojaunit.repository.ProdutoRepository;
import com.lojaunit.Lojaunit.repository.VendaRepository;

@Controller
@RequestMapping(path = "/itens_venda")
public class ItensVendaController {
	@Autowired // This means to get the bean called userRepository
	// Which is auto-generated by Spring, we will use it to handle the data

	private ItensVendaRepository itensVendaRepository;
	private ProdutoRepository produtoRepository;
	private VendaRepository vendaRepository;

	@PostMapping(path = "/add") // Map ONLY POST Requests
	public @ResponseBody String addNewItensVenda(@RequestParam Integer quantidade, @RequestParam Double valor_unitario, @RequestParam Integer idVenda, @RequestParam Integer idproduto) {

		ItensVenda itensVd = new ItensVenda();
		itensVd.setQuantidade(quantidade);
		itensVd.setValor_unitario(valor_unitario);
		Venda vendas = vendaRepository.findById(idVenda).get();
		itensVd.setVendas(vendas);
		Produto produto = produtoRepository.findById(idproduto).get();
		itensVd.setProduto(produto);

		itensVendaRepository.save(itensVd);
		return "Itens de Venda salvo com sucesso!";
	}

	@PutMapping(path = "/alterar/{id}")
	public @ResponseBody String alterarItensVenda(@RequestParam Integer quantidade, @RequestParam Double valor_unitario, @RequestParam Integer idVenda, @RequestParam Integer idproduto, @PathVariable("id") Integer id) {

		if (itensVendaRepository.existsById(id)) {
			
			ItensVenda itensVd = new ItensVenda();
			itensVd.setQuantidade(quantidade);
			itensVd.setValor_unitario(valor_unitario);
			Venda vendas = vendaRepository.findById(idVenda).get();
			itensVd.setVendas(vendas);
			Produto produto = produtoRepository.findById(idproduto).get();
			itensVd.setProduto(produto);
			
			itensVendaRepository.save(itensVd);
			return "Itens de Venda atualizado com Sucesso!";
		}
		return "Itens de Venda não encontrado!";
	}

	@GetMapping(path = "/consultar")
	public @ResponseBody Optional<ItensVenda> consultarItensVenda(@RequestParam Integer id) {

			return itensVendaRepository.findById(id);
	}

	@DeleteMapping(path = "/remover")
	public @ResponseBody String deletarItensVenda(@RequestParam Integer id) {

		if (itensVendaRepository.existsById(id)) {
			itensVendaRepository.deleteById(id);
			return "Itens de Venda removido!";
		}
		return "Itens de Venda não encontrado para exclusão!";
	}

	@GetMapping(path = "/listar")
	public @ResponseBody Iterable<ItensVenda> getAllItensVenda() {
		return itensVendaRepository.findAll();
	}

}