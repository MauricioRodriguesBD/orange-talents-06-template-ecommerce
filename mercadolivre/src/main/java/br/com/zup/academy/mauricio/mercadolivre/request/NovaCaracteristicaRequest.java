package br.com.zup.academy.mauricio.mercadolivre.request;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zup.academy.mauricio.mercadolivre.model.CaracteristicaProduto;
import br.com.zup.academy.mauricio.mercadolivre.model.Produto;

public class NovaCaracteristicaRequest {

		@NotBlank
		private String nome;
		
		@NotBlank
		private String descricao;

		public NovaCaracteristicaRequest(@NotBlank String nome, @NotBlank String descricao) {
			
			this.nome = nome;
			this.descricao = descricao;
		}

		public String getNome() {
			return nome;
		}

		public String getDescricao() {
			return descricao;
		}
		
		public CaracteristicaProduto toModel(@NotNull @Valid Produto produto) {
			return new CaracteristicaProduto(this.nome,this.descricao,produto);
		}
		
		
		
}
