package br.com.zup.academy.mauricio.mercadolivre.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String categoria;
	
	@ManyToOne
	private CategoriaMae categoriaMae;
	
	@Deprecated
	private Categoria() {}
	

	

	public Categoria(String categoria, CategoriaMae categoriaMae) {
		super();
		this.categoria = categoria;
		this.categoriaMae = categoriaMae;
	}

	public String getCategoria() {
		return categoria;
	}

	public CategoriaMae getCategoriaMae() {
		return categoriaMae;
	}
	
	
}
