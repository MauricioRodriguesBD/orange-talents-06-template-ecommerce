package br.com.zup.academy.mauricio.mercadolivre.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.academy.mauricio.mercadolivre.request.TokenRequest;
import br.com.zup.academy.mauricio.mercadolivre.response.TokenResponse;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/token")
public class TokenController {
	
		private Date umaHora() {
		return	Date.from(LocalDateTime.now()
					.plusMinutes(60)
					.atZone(ZoneId.of("America/Sao_Paulo"))
					.toInstant());
		
		}
		
		@Autowired
		private AuthenticationManager authenticationManager;
			
		@Autowired
		public TokenController(AuthenticationManager authenticationManager) {
			super();
			this.authenticationManager = authenticationManager;
		}



		@PostMapping
		public ResponseEntity<TokenResponse> criaToken(@RequestBody TokenRequest request) {
			System.out.println(request);
			
//			new UsernamePasswordAuthenticationToken(request.getEmail(),request.getSenha());
//			Authentication authenticate = authenticationManager.authenticate(request.geraOAuthentication());
			
//			User usuario = (User) authenticate.getPrincipal();
			
			String token = Jwts
			.builder()
			.setAudience("mauricio.deus@zup.com.br")
			.setIssuedAt(new Date())
			.setExpiration(umaHora())
			.signWith(SignatureAlgorithm.HS256,"minhaChaveDeSeguranca")
			.compact();
			return ResponseEntity.ok(new TokenResponse(token));
		}
}
