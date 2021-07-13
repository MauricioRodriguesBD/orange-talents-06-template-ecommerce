package br.com.zup.academy.mauricio.mercadolivre.model;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;



import br.com.zup.academy.mauricio.mercadolivre.others.GatewayPagamento;

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

	public GatewayPagamento getGateway() {
		return gateway;
	}

	@Override
	public String toString() {
		return "Compra [id=" + id + ", produto=" + produto + ", quantidade=" + quantidade + ", usuario=" + usuario
				+ ", gateway=" + gateway + "]";
	}

	
}
