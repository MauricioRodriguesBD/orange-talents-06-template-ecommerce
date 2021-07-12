package br.com.zup.academy.mauricio.mercadolivre.request;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.com.zup.academy.mauricio.mercadolivre.model.PerguntaUsuario;
import br.com.zup.academy.mauricio.mercadolivre.model.Produto;
import br.com.zup.academy.mauricio.mercadolivre.model.Usuario;



public class PerguntaUsuarioRequest {

		@NotNull
		private String titulo;
		
		@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
		public PerguntaUsuarioRequest(@NotNull String titulo) {
			super();
			this.titulo = titulo;
		}

		public String getTitulo() {
			return titulo;
		}

		public PerguntaUsuario toModel(Usuario usuario, Produto produto) {
			
			return new PerguntaUsuario(titulo, produto, usuario);
		}
		
		
		
		
}
