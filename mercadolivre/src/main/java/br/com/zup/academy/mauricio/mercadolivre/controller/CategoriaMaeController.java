package br.com.zup.academy.mauricio.mercadolivre.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.academy.mauricio.mercadolivre.model.CategoriaMae;
import br.com.zup.academy.mauricio.mercadolivre.repository.CategoriaMaeRepository;
import br.com.zup.academy.mauricio.mercadolivre.request.CategoriaMaeRequest;

@RestController
@RequestMapping("/categoriam")	
public class CategoriaMaeController {

	
	@Autowired
	private CategoriaMaeRepository repository;
	
	@PostMapping("/criar")
	@Transactional
	public ResponseEntity<?>criar(@Valid @RequestBody CategoriaMaeRequest request){
		CategoriaMae categoria = request.toModel();
		repository.save(categoria);
		return ResponseEntity.ok(categoria);
	}
	
}
