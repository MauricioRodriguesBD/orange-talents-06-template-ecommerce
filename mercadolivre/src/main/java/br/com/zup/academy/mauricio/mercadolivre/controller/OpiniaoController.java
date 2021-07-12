package br.com.zup.academy.mauricio.mercadolivre.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.academy.mauricio.mercadolivre.model.Opiniao;
import br.com.zup.academy.mauricio.mercadolivre.model.Produto;
import br.com.zup.academy.mauricio.mercadolivre.model.Usuario;
import br.com.zup.academy.mauricio.mercadolivre.repository.OpiniaoRepository;
import br.com.zup.academy.mauricio.mercadolivre.request.OpiniaoRequest;
import br.com.zup.academy.mauricio.mercadolivre.security.UsuarioLogado;

@RestController

public class OpiniaoController {

	@Autowired
	private OpiniaoRepository repository;
	
	@PersistenceContext
	private EntityManager manager;
	
	@PostMapping("/produto/{id}/opiniao")
	@Transactional
	public ResponseEntity<?> criar(@Valid @PathVariable("id")Long id, @RequestBody OpiniaoRequest request,
			@AuthenticationPrincipal UsuarioLogado usuarioLogado){
		
		Usuario usuario = usuarioLogado.get();
		Produto produto = manager.find(Produto.class,id);
		
		Opiniao opiniao =  request.toModel(usuario,produto);
		repository.save(opiniao);
		return ResponseEntity.ok(opiniao);
		
	}
	
}
