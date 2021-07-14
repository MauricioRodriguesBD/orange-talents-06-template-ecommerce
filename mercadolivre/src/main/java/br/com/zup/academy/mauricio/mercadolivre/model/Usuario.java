package br.com.zup.academy.mauricio.mercadolivre.model;

import java.time.LocalDate;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.zup.academy.mauricio.mercadolivre.config.SenhaLimpa;

@Entity
public class Usuario implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String email;

	@Length(min = 6)
	private String senha;
	
	@Deprecated
	private Usuario() {}
	
	

	@NotNull
	private LocalDate dataCriacao = LocalDate.now();

	public Usuario(String email, SenhaLimpa senhaLimpa) {
		super();
		this.email = email;

		this.senha = senhaLimpa.hash();
	}
	

	public Long getId() {
		return id;
	}


	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	

}
