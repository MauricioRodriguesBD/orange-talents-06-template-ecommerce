package br.com.zup.academy.mauricio.mercadolivre.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import br.com.zup.academy.mauricio.mercadolivre.model.Compra;
import br.com.zup.academy.mauricio.mercadolivre.model.Transacao;
import br.com.zup.academy.mauricio.mercadolivre.others.RetornoGatewayPagamento;
import br.com.zup.academy.mauricio.mercadolivre.others.StatusTransacao;



public class PayPalRequest implements RetornoGatewayPagamento {

	@Min(0)
	@Max(1)
	private int status;

	@NotBlank
	private String idTransacao;

	public PayPalRequest(@Min(0) @Max(1) int status, @NotBlank String idTransacao) {
	
		this.status = status;
		this.idTransacao = idTransacao;
	}
	
	public Transacao toTransacao(Compra compra) {
		StatusTransacao statusCalculado = this.status == 0 ? StatusTransacao.erro
				: StatusTransacao.sucesso;
		
		return new Transacao(statusCalculado, idTransacao, compra);
	}
	
}
