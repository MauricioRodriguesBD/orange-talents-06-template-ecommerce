package br.com.zup.academy.mauricio.mercadolivre.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zup.academy.mauricio.mercadolivre.others.GatewayPagamento;



public class CompraRequest {
	@Positive
	private Integer quantidade;

	@NotNull
	private Long idProduto;
	
	@NotNull
	private GatewayPagamento gateway;

	public CompraRequest(@Positive Integer quantidade, @NotNull Long idProduto) {
		super();
		this.quantidade = quantidade;
		this.idProduto = idProduto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public GatewayPagamento getGateway() {
		return gateway;
	}
	


		
}
