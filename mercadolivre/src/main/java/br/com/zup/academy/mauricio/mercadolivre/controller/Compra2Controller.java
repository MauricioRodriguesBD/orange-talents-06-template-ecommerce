package br.com.zup.academy.mauricio.mercadolivre.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.academy.mauricio.mercadolivre.model.Compra;
import br.com.zup.academy.mauricio.mercadolivre.others.EventosNovaCompra;
import br.com.zup.academy.mauricio.mercadolivre.others.RetornoGatewayPagamento;
import br.com.zup.academy.mauricio.mercadolivre.request.PagSeguroRequest;
import br.com.zup.academy.mauricio.mercadolivre.request.PayPalRequest;

@RestController

public class Compra2Controller {

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private EventosNovaCompra eventosNovaCompra;
	
	@PostMapping("/retorno-pagseguro/{id}")
	@Transactional
	public String criarPagSeguro(@Valid @PathVariable("id") Long id,
			PagSeguroRequest request) {
		return processa(id, request);
	}
	
	@PostMapping("/retornopaypal/{id}")
	@Transactional
	public String criaPayPal(@Valid @PathVariable("id")Long id,
			 PayPalRequest request) {
		return processa(id,request);
		
	}
	private String processa(Long id,RetornoGatewayPagamento retornoGatewayPagamento) {
		
		Compra compra = manager.find(Compra.class,id);
		
		compra.adicionaTransacao(retornoGatewayPagamento);
		
		manager.merge(compra);
		
		eventosNovaCompra.processa(compra);
		
		return compra.toString();
		
		
		
	}
}
