package com.lojaunit.Lojaunit.controller;

import java.sql.Timestamp;
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

import com.lojaunit.Lojaunit.model.Clientes;
import com.lojaunit.Lojaunit.model.FormaDePagamento;
import com.lojaunit.Lojaunit.model.Venda;
import com.lojaunit.Lojaunit.repository.ClienteRepository;
import com.lojaunit.Lojaunit.repository.FormaDePagamentoRepository;
import com.lojaunit.Lojaunit.repository.VendaRepository;

@Controller
@RequestMapping(path = "/venda")
public class VendaController {
	@Autowired
	private ClienteRepository Clienterepository;
	@Autowired
	private FormaDePagamentoRepository formaDePagamentoRepository;
	@Autowired
	private VendaRepository vendaRepository;

	@PostMapping(path = "/add") // Map ONLY POST Requests
	public @ResponseBody String addNewVenda(@RequestParam Timestamp datahora, @RequestParam Double valorTotal,
											@RequestParam Integer id_cliente, @RequestParam Integer idFormaPagamento) {

		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

		Venda venda = new Venda();
		venda.setDatahora(datahora);
		venda.setValorTotal(valorTotal);
		Clientes cli = Clienterepository.findById(id_cliente).get();
		venda.setCliente(cli);
		FormaDePagamento formaPg = formaDePagamentoRepository.findById(idFormaPagamento).get();
		venda.setFormadepagamento(formaPg);

		vendaRepository.save(venda);
		return "Venda salva com sucesso!";
	}

	@PutMapping(path = "/alterar/{id}")
	public @ResponseBody String alterarVenda(@RequestParam Timestamp datahora, @RequestParam Double valorTotal,@RequestParam Integer id_cliente, 
											 @RequestParam Integer idFormadePagamento, @PathVariable("id") Integer id) {

		if (vendaRepository.existsById(id)) {
			
			Venda venda = new Venda();
			venda.setDatahora(datahora);
			venda.setValorTotal(valorTotal);
			Clientes cli = Clienterepository.findById(id_cliente).get();
			venda.setCliente(cli);
			FormaDePagamento formaPg = formaDePagamentoRepository.findById(idFormadePagamento).get();
			venda.setFormadepagamento(formaPg);

			vendaRepository.save(venda);
			return "Venda atualizado com Sucesso!";
		}
		return "Venda não encontrado!";
	}

	@GetMapping(path = "/consultar/{id}")
	public @ResponseBody Optional<Venda> consultarVenda(@PathVariable("id") Integer id) {

			return vendaRepository.findById(id);
	}

	@DeleteMapping(path = "/remover/{id}")
	public @ResponseBody String deletarVenda(@PathVariable("id") Integer id) {

		if (vendaRepository.existsById(id)) {
			vendaRepository.deleteById(id);
			return "Itens de Venda removido!";
		}
		return "Venda não encontrado para exclusão!";
	}

	@GetMapping(path = "/listar")
	public @ResponseBody Iterable<Venda> getAllVenda() {
		return vendaRepository.findAll();
	}

}