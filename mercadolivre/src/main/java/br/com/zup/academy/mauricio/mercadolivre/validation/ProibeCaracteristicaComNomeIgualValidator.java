package br.com.zup.academy.mauricio.mercadolivre.validation;

import java.util.Set;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


import br.com.zup.academy.mauricio.mercadolivre.request.ProdutoRequest;

public class ProibeCaracteristicaComNomeIgualValidator implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		return ProdutoRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if(errors.hasErrors()) {
			return ;
		}
		
		ProdutoRequest request = (ProdutoRequest) target;
		Set<String> nomesIguais = request.buscaCaracteristicasIguais();
		if(!nomesIguais.isEmpty()) {
			errors.rejectValue("caracteristicas", null, "Olha, você tem caracteristicas iguais "+nomesIguais);
		}
	}
}
