package br.com.zup.academy.mauricio.mercadolivre.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.academy.mauricio.mercadolivre.dto.PaginaDetalheDto;
import br.com.zup.academy.mauricio.mercadolivre.model.Produto;
import br.com.zup.academy.mauricio.mercadolivre.repository.ProdutoRepository;

@RestController
public class PaginaDetalheController {

	@Autowired
	private ProdutoRepository repository;
	
	@GetMapping("/produto/{id}")
	public ResponseEntity<PaginaDetalheDto>buscar(@PathVariable ("id")Long id){
		Optional<Produto> produto = repository.findById(id);
		if(produto.isPresent()) {
			return ResponseEntity.ok(new PaginaDetalheDto(produto.get()));
			}
		return ResponseEntity.notFound().build();
	}
}
