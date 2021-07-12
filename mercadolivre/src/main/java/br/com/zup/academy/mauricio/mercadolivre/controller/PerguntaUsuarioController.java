package br.com.zup.academy.mauricio.mercadolivre.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import br.com.zup.academy.mauricio.mercadolivre.model.PerguntaUsuario;
import br.com.zup.academy.mauricio.mercadolivre.model.Produto;
import br.com.zup.academy.mauricio.mercadolivre.model.Usuario;
import br.com.zup.academy.mauricio.mercadolivre.others.Emails;
import br.com.zup.academy.mauricio.mercadolivre.repository.PerguntaUsuarioRepository;
import br.com.zup.academy.mauricio.mercadolivre.request.PerguntaUsuarioRequest;
import br.com.zup.academy.mauricio.mercadolivre.security.UsuarioLogado;

@RestController
@RequestMapping("/produto")
public class PerguntaUsuarioController {

	@Autowired
	private PerguntaUsuarioRepository repository;
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private Emails emails;
	
	
	@PostMapping("/{id}/pergunta")
	@Transactional
	public ResponseEntity<?> criar(@PathVariable("id") Long id,
			@RequestBody PerguntaUsuarioRequest request,
			@AuthenticationPrincipal UsuarioLogado usuarioLogado){
		Usuario usuario = usuarioLogado.get();
		Produto produto = manager.find(Produto.class,id);
		PerguntaUsuario pergunta = request.toModel(usuario,produto);
		repository.save(pergunta);
		
		emails.novaPergunta(pergunta);
		
		
		 
		return ResponseEntity.ok().build();
		
	}
}
