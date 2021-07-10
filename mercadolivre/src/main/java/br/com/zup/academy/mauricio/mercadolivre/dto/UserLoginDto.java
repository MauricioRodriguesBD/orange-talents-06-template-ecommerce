package br.com.zup.academy.mauricio.mercadolivre.dto;


public class UserLoginDto {

	private String email;
	
	private String senha;
	
	
	
	
	public UserLoginDto(String email, String senha) {
		this.email = email;
		this.senha = senha;
	
	}




	public String getEmail() {
		return email;
	}




	public String getSenha() {
		return senha;
	}


	
	
}
