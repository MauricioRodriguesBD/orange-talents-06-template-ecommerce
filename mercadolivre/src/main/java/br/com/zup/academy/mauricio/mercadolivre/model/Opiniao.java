package br.com.zup.academy.mauricio.mercadolivre.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


import org.hibernate.validator.constraints.Length;

@Entity
public class Opiniao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Min(1)
	@Max(5)
	private Integer nota;

	@NotNull
	private String titulo;

	@Length(max = 500)
	private String descricao;

	@ManyToOne
	private Produto produto;
	
	@ManyToOne
	private Usuario usuario;

	@Deprecated
	private Opiniao() {
	}

	
	public Opiniao(@Min(1) @Max(5) Integer nota, @NotNull String titulo, @Length(max = 500) String descricao,
			Produto produto, Usuario usuario) {
		super();
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
		this.produto = produto;
		this.usuario = usuario;
	}


	public Integer getNota() {
		return nota;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public Produto getProduto() {
		return produto;
	}
	

}
