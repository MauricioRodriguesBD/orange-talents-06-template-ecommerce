package br.com.zup.academy.mauricio.mercadolivre.others;

import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.academy.mauricio.mercadolivre.model.Compra;


public enum GatewayPagamento {
	pagseguro {
		@Override
		public
		String criaUrlRetorno(Compra compra,
				UriComponentsBuilder uriComponentsBuilder) {
			String urlRetornoPagseguro = uriComponentsBuilder
					.path("/retorno-pagseguro/{id}")
					.buildAndExpand(compra.getId()).toString();

			return "pagseguro.com/" + compra.getId() + "?redirectUrl="
					+ urlRetornoPagseguro;
		}
	},
	paypal {
		@Override
		public
		String criaUrlRetorno(Compra compra,
				UriComponentsBuilder uriComponentsBuilder) {
			String urlRetornoPaypal = uriComponentsBuilder
					.path("/retorno-paypal/{id}").buildAndExpand(compra.getId())
					.toString();

			return "paypal.com/" + compra.getId() + "?redirectUrl=" + urlRetornoPaypal;
		}
	};

	public abstract String criaUrlRetorno(Compra compra,
			UriComponentsBuilder uriComponentsBuilder);
}

