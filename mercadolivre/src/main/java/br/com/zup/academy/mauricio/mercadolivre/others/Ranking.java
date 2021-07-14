package br.com.zup.academy.mauricio.mercadolivre.others;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import br.com.zup.academy.mauricio.mercadolivre.model.Compra;

@Service
public class Ranking implements EventoCompraSucesso {

	@Override
	public void processa(Compra compra) {
		Assert.isTrue(compra.processadaComSucesso()," Compra nao processada "+compra);
		
		RestTemplate restTemplate = new RestTemplate();
		Map<String, Object> request = Map.of("id", compra.getId(),
				"idDonoProduto", compra.getDonoProduto().getId());		

		restTemplate.postForEntity("http://localhost:8080/ranking",
				request, String.class);		
	}
}
