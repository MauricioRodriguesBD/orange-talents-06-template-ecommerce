package br.com.zup.academy.mauricio.mercadolivre.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.academy.mauricio.mercadolivre.model.Compra;
import br.com.zup.academy.mauricio.mercadolivre.model.Produto;
import br.com.zup.academy.mauricio.mercadolivre.model.Usuario;
import br.com.zup.academy.mauricio.mercadolivre.others.Emails;
import br.com.zup.academy.mauricio.mercadolivre.others.GatewayPagamento;
import br.com.zup.academy.mauricio.mercadolivre.request.CompraRequest;
import br.com.zup.academy.mauricio.mercadolivre.security.UsuarioLogado;

@RestController
@RequestMapping("/compra")
public class CompraController {

	

	@PersistenceContext
	private EntityManager manager;

	

	@Autowired
	private Emails email;

	@PostMapping("/produto")
	@Transactional
	public String cria(@Valid @RequestBody  CompraRequest request,
			@AuthenticationPrincipal UsuarioLogado usuarioLogado, UriComponentsBuilder uriComponentsBuilder)
			throws BindException {
		
		Usuario usuario = usuarioLogado.get();
		int quantidade = request.getQuantidade();
		

		Produto produtoASerComprado = manager.find(Produto.class, request.getIdProduto());

		
		boolean abateu = produtoASerComprado.abataEstoque(quantidade);

		if (abateu) {
			GatewayPagamento gateway = request.getGateway();

			Compra compra = new Compra(produtoASerComprado, quantidade, usuario, gateway);
			manager.persist(compra);
			email.novaCompra(compra);

			return compra.urlRedirecionamento(uriComponentsBuilder);
		}

		BindException problemaComEstoque = new BindException(request, "CompraRequest");
		problemaComEstoque.reject(null, "Não foi possível realizar a compra por conta do estoque");

		throw problemaComEstoque;

	}
}
