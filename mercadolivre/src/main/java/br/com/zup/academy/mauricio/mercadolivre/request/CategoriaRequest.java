package br.com.zup.academy.mauricio.mercadolivre.request;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zup.academy.mauricio.mercadolivre.model.Categoria;
import br.com.zup.academy.mauricio.mercadolivre.model.CategoriaMae;
import br.com.zup.academy.mauricio.mercadolivre.validation.ExistsId;

public class CategoriaRequest {
		
		@NotBlank
		private String categoria;
		
		@NotNull
		@ExistsId(domainClass = CategoriaMae.class, fieldName = "id")
		private Long idCategoriaMae;

		public CategoriaRequest(@NotBlank String categoria, @NotNull Long idCategoriaMae) {
			super();
			this.categoria = categoria;
			this.idCategoriaMae = idCategoriaMae;
		}

		public String getCategoria() {
			return categoria;
		}

		public Long getIdCategoriaMae() {
			return idCategoriaMae;
		}

		public Categoria toModel(EntityManager manager) {
			@NotNull
			CategoriaMae categoriaMae = manager.find(CategoriaMae.class,idCategoriaMae);
			return new Categoria(this.categoria,categoriaMae);
		}
		
		
}
