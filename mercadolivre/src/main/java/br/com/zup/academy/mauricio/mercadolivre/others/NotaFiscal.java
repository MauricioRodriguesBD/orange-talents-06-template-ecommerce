package br.com.zup.academy.mauricio.mercadolivre.others;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import br.com.zup.academy.mauricio.mercadolivre.model.Compra;

@Service
public class NotaFiscal implements EventoCompraSucesso {

	@Override
	public void processa(Compra compra) {
		Assert.isTrue(compra.processadaComSucesso(),"Opa opa opa compra nao concluida com sucesso "+compra);
		
		RestTemplate restTemplate = new RestTemplate();
		Map<String, Object> request = Map.of("idCompra", compra.getId(),
				"idComprador", compra.getUsuario().getId());		

		restTemplate.postForEntity("http://localhost:8080/notas-fiscais",
				request, String.class);
	}

}
