package br.com.zup.academy.mauricio.mercadolivre.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.zup.academy.mauricio.mercadolivre.model.Usuario;


@Configuration
public class AppUserDetailsMapper implements UserDetailsMapper{

	@Override
	public UserDetails map(Object shouldBeASystemUser) {						
		return new UsuarioLogado((Usuario)shouldBeASystemUser);
	}

}
