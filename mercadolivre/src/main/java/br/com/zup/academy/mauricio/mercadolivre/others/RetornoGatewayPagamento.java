package br.com.zup.academy.mauricio.mercadolivre.others;

import br.com.zup.academy.mauricio.mercadolivre.model.Compra;
import br.com.zup.academy.mauricio.mercadolivre.model.Transacao;

public interface RetornoGatewayPagamento {

	Transacao toTransacao(Compra compra);
}
