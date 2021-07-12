package br.com.zup.academy.mauricio.mercadolivre.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.zup.academy.mauricio.mercadolivre.model.Opiniao;
import br.com.zup.academy.mauricio.mercadolivre.model.Produto;
import br.com.zup.academy.mauricio.mercadolivre.model.Usuario;


public class OpiniaoRequest {

	@Min(1)
	@Max(5)
	private Integer nota;
	
	@NotNull
	private String titulo;
	
	@Length(max = 500)
	private String descricao;
	

	
	
	

	public Integer getNota() {
		return nota;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}
	


	public Opiniao toModel(Usuario usuario, Produto produto) {
		
		return new Opiniao(this.nota,this.titulo,this.descricao,produto,usuario);
	}
	
	
	
}
