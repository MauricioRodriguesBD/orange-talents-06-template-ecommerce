package br.com.zup.academy.mauricio.mercadolivre.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.academy.mauricio.mercadolivre.request.CompraNFRequest;
import br.com.zup.academy.mauricio.mercadolivre.request.RankingNovaCompraRequest;

@RestController
public class OutrosSistemasController {

	@PostMapping("/notas-fiscais")
	public void cria(@Valid @RequestBody CompraNFRequest request) 
			throws InterruptedException {
	System.out.println("criando nota" + request);
	Thread.sleep(150);
}
	
	@PostMapping("/ranking")
	public void criar (@Valid @RequestBody RankingNovaCompraRequest request)
			throws InterruptedException{
		System.out.println("Criando Ranking" + request);
		Thread.sleep(150);
	}
}
