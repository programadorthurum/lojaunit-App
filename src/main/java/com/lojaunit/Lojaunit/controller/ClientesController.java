package com.lojaunit.Lojaunit.controller;

import java.sql.Date;
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
import com.lojaunit.Lojaunit.repository.ClienteRepository;

@Controller
@RequestMapping(path = "/clientes") 
public class ClientesController {
	@Autowired // This means to get the bean called userRepository
	// Which is auto-generated by Spring, we will use it to handle the data

	private ClienteRepository Clienterepository;

	@PostMapping(path = "/add") // Map ONLY POST Requests
	public @ResponseBody String addNewCliente(@RequestParam String nome, @RequestParam String cpf,
			@RequestParam String email, @RequestParam Date datanascimento, @RequestParam String sexo,
			@RequestParam String nomeSocial, @RequestParam String apelido, @RequestParam String telefone) {

		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

		Clientes cli = new Clientes();
		cli.setNome(nome);
		cli.setCpf(cpf);
		cli.setEmail(email);
		cli.setDataNascimento(datanascimento);
		cli.setSexo(sexo);
		cli.setNomeSocial(nomeSocial);
		cli.setApelido(apelido);
		cli.setTelefone(telefone);

		Clienterepository.save(cli);
		return "Cliente salvo com sucesso!!!";
	}


	@PutMapping(path = "/alterar/{id}")	
	public @ResponseBody String alterarCliente(@RequestParam String nome, @RequestParam String cpf, 
												@RequestParam String email, @RequestParam Date dataNascimento, 
												@RequestParam String sexo, @RequestParam String nomeSocial, 
												@RequestParam String apelido, @RequestParam String telefone,
												@PathVariable("id") Integer id) {

		if (Clienterepository.existsById(id)) {	
			Clientes cliente = new Clientes();			
			cliente.setId(id);			
			cliente.setNome(nome);			
			cliente.setCpf(cpf);			
			cliente.setEmail(email);			
			cliente.setDataNascimento(dataNascimento);			
			cliente.setSexo(sexo);			
			cliente.setNomeSocial(nomeSocial);			
			cliente.setApelido(apelido);			
			cliente.setTelefone(telefone);
			
			Clienterepository.save(cliente);		
			return "Cliente atualizado com Sucesso!";
			}
		return "Cliente não encontrado!";
	}

	@PutMapping(path = "/alterar/especifico")	
	public @ResponseBody String alterarClienteEspecifico(@RequestParam String nome, @RequestParam String email,
												@RequestParam String nomeSocial, @RequestParam String apelido, 
												@RequestParam String telefone, @PathVariable("id") Integer id) {

		if (Clienterepository.existsById(id)) {	
			Clientes cliente = new Clientes();			
			cliente.setId(id);			
			cliente.setNome(nome);			
			cliente.setEmail(email);			
			cliente.setNomeSocial(nomeSocial);			
			cliente.setApelido(apelido);			
			cliente.setTelefone(telefone);
			
			Clienterepository.save(cliente);		
			return "Cliente atualizado com Sucesso!";
			}
		return "Cliente não encontrado!";
	}

	
	@GetMapping(path = "/consultar")
	public @ResponseBody Optional<Clientes> consultarClientes(@RequestParam Integer id){

			return Clienterepository.findById(id);
	}
	
	@DeleteMapping(path = "/remover")
	public @ResponseBody String deletarClientes(@RequestParam Integer id) {
	
		if (Clienterepository.existsById(id)) {
		Clienterepository.deleteById(id);
		return "Cliente removido!";
		}
		return "Cliente não encontrado para exclusão!";
	}

	@GetMapping(path = "/listar")
	public @ResponseBody Iterable<Clientes> getAllClientes() {
		return Clienterepository.findAll();
	}

}