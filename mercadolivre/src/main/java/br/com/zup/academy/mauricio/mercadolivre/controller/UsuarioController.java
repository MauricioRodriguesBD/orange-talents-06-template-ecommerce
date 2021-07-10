package br.com.zup.academy.mauricio.mercadolivre.controller;



import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.academy.mauricio.mercadolivre.dto.UserLoginDto;
import br.com.zup.academy.mauricio.mercadolivre.model.Usuario;
import br.com.zup.academy.mauricio.mercadolivre.repository.UsuarioRepository;
import br.com.zup.academy.mauricio.mercadolivre.request.UsuarioRequest;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioRepository repository;
	
	
	
	@PostMapping("/criar")
	@Transactional
	public ResponseEntity<?> criar(@Valid @RequestBody UsuarioRequest request){
		Usuario usuario = request.toModel();
		repository.save(usuario);
		return ResponseEntity.ok(usuario);
		
	}
	

	
}
