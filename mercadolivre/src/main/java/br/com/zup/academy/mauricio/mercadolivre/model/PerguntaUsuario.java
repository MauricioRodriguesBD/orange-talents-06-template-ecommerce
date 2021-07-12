package br.com.zup.academy.mauricio.mercadolivre.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;


@Entity
public class PerguntaUsuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String titulo;
	
	@ManyToOne
	private Produto produto;
	
	@ManyToOne
	private Usuario usuario;
	
	@NotNull
	private LocalDate data = LocalDate.now();
	
	@Deprecated
	private PerguntaUsuario() {}

	public PerguntaUsuario(String titulo, Produto produto, Usuario usuario) {
		
		this.titulo = titulo;
		this.produto = produto;
		this.usuario = usuario;
	}

	public String getTitulo() {
		return titulo;
	}

	public Produto getProduto() {
		return produto;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	public Usuario getDonoProduto() {
		return produto.getDono();
	}
	
	
	
}
