package br.com.zup.academy.mauricio.mercadolivre.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.academy.mauricio.mercadolivre.model.Categoria;
import br.com.zup.academy.mauricio.mercadolivre.repository.CategoriaRepository;
import br.com.zup.academy.mauricio.mercadolivre.request.CategoriaRequest;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

	
	@Autowired
	private CategoriaRepository repository;
	
	@PersistenceContext
	private EntityManager manager;
	
	@PostMapping("/criar")
	@Transactional
	public ResponseEntity<?>criar(@Valid @RequestBody CategoriaRequest request){
		Categoria categoria = request.toModel(manager);
		repository.save(categoria);
		return ResponseEntity.ok(categoria);
	}
}
