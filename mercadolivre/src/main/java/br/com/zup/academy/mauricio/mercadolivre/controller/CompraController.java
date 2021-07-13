package br.com.zup.academy.mauricio.mercadolivre.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import br.com.zup.academy.mauricio.mercadolivre.repository.ProdutoRepository;
import br.com.zup.academy.mauricio.mercadolivre.repository.UsuarioRepository;
import br.com.zup.academy.mauricio.mercadolivre.request.CompraRequest;

@RestController
@RequestMapping("/compra")
public class CompraController {

	@Autowired
	private ProdutoRepository produtoRepository;

	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private Emails email;

	@PostMapping("/produto")
	@Transactional
	public String cria(@RequestBody @Valid CompraRequest request,
			UriComponentsBuilder uriComponentsBuilder) throws BindException {

	
		Produto produtoASerComprado = manager.find(Produto.class,
				request.getIdProduto());

		int quantidade = request.getQuantidade();
		boolean abateu = produtoASerComprado.abataEstoque(quantidade);

		if (abateu) {
			Usuario comprador = usuarioRepository
					.findByEmail("mauricio.deus@zup.com.br").get();
	
			GatewayPagamento gateway = request.getGateway();

		
			Compra compra = new Compra(produtoASerComprado, quantidade, comprador, gateway);
			manager.persist(compra);
			
			if(gateway.equals(GatewayPagamento.pagseguro)) {
				String urlRetornoPagSeguro = uriComponentsBuilder.path("/retorno-pagseguro/{id}").buildAndExpand(compra.getId()).toString();
				
				return "paypal.com/" + compra.getId() + "?redirectUrl="+urlRetornoPagSeguro;
			}
			
			return compra.toString();						
		}

		BindException problemaComEstoque = new BindException(request,
				"CompraRequest");
		problemaComEstoque.reject(null,
				"Não foi possível realizar a compra por conta do estoque");

		throw problemaComEstoque;
		
		
	
}
}
