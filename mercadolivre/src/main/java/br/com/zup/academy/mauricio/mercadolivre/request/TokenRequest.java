package br.com.zup.academy.mauricio.mercadolivre.request;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

public class TokenRequest {

	private String email;

	private String senha;

	public TokenRequest(String email, String senha) {
		super();
		this.email = email;
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}
	


	
	public UsernamePasswordAuthenticationToken geraOAutenticador() {
		return new UsernamePasswordAuthenticationToken(this.email,this.senha); 
	}
	

}
