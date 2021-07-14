package br.com.zup.academy.mauricio.mercadolivre.model;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.util.Assert;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.academy.mauricio.mercadolivre.others.GatewayPagamento;
import br.com.zup.academy.mauricio.mercadolivre.others.RetornoGatewayPagamento;
import br.com.zup.academy.mauricio.mercadolivre.request.PagSeguroRequest;

@Entity
public class Compra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Valid
	@ManyToOne
	private Produto produto;

	@Positive
	private Integer quantidade;

	@ManyToOne
	private Usuario usuario;

	@Enumerated
	@NotNull
	private GatewayPagamento gateway;

	@OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
	private Set<Transacao> transacao = new HashSet<>();

	@Deprecated
	private Compra() {
	}

	public Compra(@NotNull @Valid Produto produto, @Positive int quantidade, @NotNull @Valid Usuario usuario,
			@NotNull GatewayPagamento gateway) {
		this.produto = produto;
		this.quantidade = quantidade;
		this.usuario = usuario;
		this.gateway = gateway;
	}

	public Long getId() {
		return id;
	}

	public Produto getProduto() {
		return produto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Usuario getDonoProduto() {
		return produto.getDono();
	}

	
	public String urlRedirecionamento(
			UriComponentsBuilder uriComponentsBuilder) {
		return this.gateway.criaUrlRetorno(this,uriComponentsBuilder);
	}

	@Override
	public String toString() {
		return "Compra [id=" + id + ", produto=" + produto + ", quantidade=" + quantidade + ", usuario=" + usuario
				+ ", gateway=" + gateway + "]";
	}

	public void tentativaPagamento(PagSeguroRequest request) {
		this.transacao.add(request.toTransacao(this));

	}

	public void adicionaTransacao(@Valid RetornoGatewayPagamento request) {
		Transacao novaTransacao = request.toTransacao(this);

		Assert.state(!this.transacao.contains(novaTransacao),
				"Já existe uma transacao igual a essa processada " + novaTransacao);

		Assert.state(transacoesConcluidasComSucesso().isEmpty(), "Essa compra já foi concluída com sucesso");

		this.transacao.add(novaTransacao);
	}

	private Set<Transacao> transacoesConcluidasComSucesso() {
		Set<Transacao> transacoesConcluidasComSucesso = this.transacao.stream().filter(Transacao::concluidaComSucesso)
				.collect(Collectors.toSet());

		Assert.isTrue(transacoesConcluidasComSucesso.size() <= 1,
				"Erro : Existe mais de uma transacao concluida na compra " + this.id);

		return transacoesConcluidasComSucesso;
	}

	public boolean processadaComSucesso() {
		return !transacoesConcluidasComSucesso().isEmpty();
	}

}
