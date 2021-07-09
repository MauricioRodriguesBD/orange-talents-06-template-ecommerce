package br.com.zup.academy.mauricio.mercadolivre.request;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.com.zup.academy.mauricio.mercadolivre.model.CategoriaMae;
import br.com.zup.academy.mauricio.mercadolivre.validation.UniqueValue;

public class CategoriaMaeRequest {
	
	@UniqueValue(domainClass = CategoriaMae.class,fieldName = "categoria")
	private String categoria;

	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public CategoriaMaeRequest(String categoria) {
		
		this.categoria = categoria;
	}

	
	
	public String getCategoria() {
		return categoria;
	}



	public CategoriaMae toModel() {
		
		return new CategoriaMae(this.categoria);
	}
	
	
}
