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

import com.lojaunit.Lojaunit.model.Faq;
import com.lojaunit.Lojaunit.model.Produto;
import com.lojaunit.Lojaunit.repository.FaqRepository;
import com.lojaunit.Lojaunit.repository.ProdutoRepository;


@Controller
@RequestMapping(path = "/faq") 
public class FaqController {
	@Autowired // This means to get the bean called userRepository
	// Which is auto-generated by Spring, we will use it to handle the data

	private FaqRepository Faqrepository;

	private ProdutoRepository Produtorepository;
	
	@PostMapping(path = "/add") // Map ONLY POST Requests
	public @ResponseBody String addNewFaq(@RequestParam Timestamp datahora, @RequestParam String texto, @RequestParam Integer id_produto) {

		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

		Faq faq = new Faq();
		faq.setDatahora(datahora);
		Produto produto = Produtorepository.findById(id_produto).get();
		faq.setProduto(produto);
		faq.setTexto(texto);

		Faqrepository.save(faq);
		return "FAQ salvo com sucesso!!!";
	}


	@PutMapping(path = "/alterar/{id}")	
	public @ResponseBody String alterarFaq(@RequestParam Timestamp datahora, @RequestParam String texto,
												@PathVariable("id") Integer id) {

		if (Faqrepository.existsById(id)) {	
			Faq faq = new Faq();			
			faq.setId(id);			
			faq.setDatahora(datahora);
			faq.setTexto(texto);			
			
			Faqrepository.save(faq);		
			return "FAQ atualizado com Sucesso!";
			}
		return "FAQ não encontrado!";
	}

	
	@GetMapping(path = "/consultar")
	public @ResponseBody Optional<Faq> consultarFaq(@RequestParam Integer id){

			return Faqrepository.findById(id);
	}
	
	@DeleteMapping(path = "/remover/{id}")
	public @ResponseBody String deletarFaq(@PathVariable("id") Integer id) {
	
		if (Faqrepository.existsById(id)) {
			Faqrepository.deleteById(id);
		return "FAQ removido!";
		}
		return "FAQ não encontrado para exclusão!";
	}

	@GetMapping(path = "/listar")
	public @ResponseBody Iterable<Faq> getAllFaq() {
		return Faqrepository.findAll();
	}

}