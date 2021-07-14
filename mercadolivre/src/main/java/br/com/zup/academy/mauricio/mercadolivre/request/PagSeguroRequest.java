package br.com.zup.academy.mauricio.mercadolivre.request;

import javax.validation.constraints.NotNull;

import br.com.zup.academy.mauricio.mercadolivre.model.Compra;
import br.com.zup.academy.mauricio.mercadolivre.model.Transacao;
import br.com.zup.academy.mauricio.mercadolivre.others.RetornoGatewayPagamento;
import br.com.zup.academy.mauricio.mercadolivre.others.StatusRetornoPagSeguro;

public class PagSeguroRequest implements RetornoGatewayPagamento {

	@NotNull
	private String IdTransacao;
	
	@NotNull
	private StatusRetornoPagSeguro status;

	public PagSeguroRequest(@NotNull String idTransacao, StatusRetornoPagSeguro status) {
		
		IdTransacao = idTransacao;
		this.status = status;
	}

	@Override
	public Transacao toTransacao(Compra compra) {
		return new Transacao(status.normaliza(),IdTransacao,compra);
	}

	@Override
	public String toString() {
		return "PagSeguroRequest [IdTransacao=" + IdTransacao + ", status=" + status + "]";
	}

	
	


	
	
}
