package br.com.zup.academy.mauricio.mercadolivre.others;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zup.academy.mauricio.mercadolivre.model.Compra;

@Service
public class EventosNovaCompra {

	@Autowired
	private Set<EventoCompraSucesso> eventosCompraSucesso;

	public void processa(Compra compra) {
		
		if (compra.processadaComSucesso()) {
			
			eventosCompraSucesso.forEach(evento -> evento.processa(compra));
			
		} else {

		}
	}
}
