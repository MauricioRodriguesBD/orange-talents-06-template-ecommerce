package br.com.zup.academy.mauricio.mercadolivre.response;

public class TokenResponse {

	private String token;

	private String tipo;

	public TokenResponse(String token) {
		
		this.token = token;
		this.tipo = "JWT";
	}

	public String getToken() {
		return token;
	}

	public String getTipo() {
		return tipo;
	}
	
	

	
		
}
