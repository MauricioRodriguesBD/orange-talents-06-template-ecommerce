package br.com.zup.academy.mauricio.mercadolivre.controller;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.academy.mauricio.mercadolivre.model.Produto;
import br.com.zup.academy.mauricio.mercadolivre.model.Usuario;
import br.com.zup.academy.mauricio.mercadolivre.repository.ProdutoRepository;
import br.com.zup.academy.mauricio.mercadolivre.repository.UsuarioRepository;
import br.com.zup.academy.mauricio.mercadolivre.request.ImagemRequest;
import br.com.zup.academy.mauricio.mercadolivre.request.ProdutoRequest;
import br.com.zup.academy.mauricio.mercadolivre.request.UploaderFake;
import br.com.zup.academy.mauricio.mercadolivre.validation.ProibeCaracteristicaComNomeIgualValidator;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;

	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private UploaderFake uploaderFake;

	@InitBinder(value = "novoProdutoRequest")
	public void Init(WebDataBinder webDataBinder) {
		webDataBinder.addValidators(new ProibeCaracteristicaComNomeIgualValidator());
	}

	@PostMapping("/criar")
	@Transactional
	public ResponseEntity<?> criar(@Valid @RequestBody ProdutoRequest request) {
		Usuario dono = usuarioRepository.findByEmail("mauricio.deus@zup.com.br").get();
		Produto produto = request.toModel(manager, dono);

		produtoRepository.save(produto);
		return ResponseEntity.ok(produto);

	}

	@PostMapping("/{id}/imagem")
	@Transactional
	public ResponseEntity<?> addImagem(@PathVariable("id") Long id, @Valid ImagemRequest request) {

		Set<String> links = uploaderFake.envia(request.getImagem());
		System.out.println(links);
//		Optional<Produto> produto = produtoRepository.findById(id);
//		produtoRepository.findById(id);
		 Produto produto = manager.find(Produto.class, id);
		 produto.associaImagem(links);
		 manager.merge(produto);
		 return ResponseEntity.ok(produto);
//		 return produto.toString();
	}

}
