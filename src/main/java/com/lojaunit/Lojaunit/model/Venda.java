package com.lojaunit.Lojaunit.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="vendas")
public class Venda {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name= "id_cliente")
	private Clientes cliente;
	
	private Timestamp datahora;
	private Double valorTotal;
	
	@ManyToOne
	@JoinColumn(name="id_forma_pagamento")
	private FormaDePagamento formadepagamento;
	
	
	public Clientes getCliente() {
		return cliente;
	}
	public void setCliente(Clientes cliente) {
		this.cliente = cliente;
	}
	public FormaDePagamento getFormadepagamento() {
		return formadepagamento;
	}
	public void setFormadepagamento(FormaDePagamento formadepagamento) {
		this.formadepagamento = formadepagamento;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Timestamp getDatahora() {
		return datahora;
	}
	public void setDatahora(Timestamp datahora) {
		this.datahora = datahora;
	}
	public Double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	
}
