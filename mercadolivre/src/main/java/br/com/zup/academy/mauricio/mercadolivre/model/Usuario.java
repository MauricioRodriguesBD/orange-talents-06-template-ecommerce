package br.com.zup.academy.mauricio.mercadolivre.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.zup.academy.mauricio.mercadolivre.config.SenhaLimpa;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String email;

	@Length(min = 6)
	private String senha;
	
	

	@NotNull
	private LocalDate dataCriacao = LocalDate.now();

	public Usuario(String email, SenhaLimpa senhaLimpa) {
		super();
		this.email = email;

		this.senha = senhaLimpa.hash();
	}

}
