package br.com.zup.academy.mauricio.mercadolivre.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CategoriaMae {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String categoria;
	
	@Deprecated
	private CategoriaMae() {}

	public CategoriaMae(String categoria) {
		super();
		this.categoria = categoria;
	}

	public String getCategoria() {
		return categoria;
	}

}
