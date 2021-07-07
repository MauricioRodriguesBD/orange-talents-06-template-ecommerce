package br.com.zup.academy.mauricio.mercadolivre.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.zup.academy.mauricio.mercadolivre.config.SenhaLimpa;
import br.com.zup.academy.mauricio.mercadolivre.model.Usuario;
import br.com.zup.academy.mauricio.mercadolivre.validation.UniqueValue;

public class UsuarioRequest {

	@Email
	@NotNull
	@UniqueValue(domainClass = Usuario.class, fieldName = "email")
	private String email;

	@Length(min = 6)
	@NotNull
	private String senha;
	
	

	

	public UsuarioRequest(@Email @NotNull String email, @Length(min = 6) @NotNull String senha) {
		super();
		this.email = email;
		this.senha = senha;
	}


	public Usuario toModel() {

		return new Usuario(this.email,new SenhaLimpa(senha));
	}

}
