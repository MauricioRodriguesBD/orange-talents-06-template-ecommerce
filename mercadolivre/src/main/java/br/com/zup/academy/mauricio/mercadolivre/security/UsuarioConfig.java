package br.com.zup.academy.mauricio.mercadolivre.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
 



@EnableWebSecurity
@Configuration
public class UsuarioConfig  extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private TokenManager tokenManager;

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http
			.authorizeRequests()
			.antMatchers("/token").permitAll()
			.antMatchers("/usuario/criar").permitAll()
			.anyRequest()
			.authenticated()
				.and()
			.cors()
				.and()
			.csrf().disable()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
			
			
		}
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.inMemoryAuthentication()
		.withUser("mauricio.deus@zup.com.br")
		.password("{noop}123456")
		.roles(String.valueOf(new ArrayList<>()));
		}
		
		@Override
		@Bean(BeanIds.AUTHENTICATION_MANAGER)
		public AuthenticationManager authenticationManagerBean() throws Exception {
			return super.authenticationManagerBean();
		}
		
}
