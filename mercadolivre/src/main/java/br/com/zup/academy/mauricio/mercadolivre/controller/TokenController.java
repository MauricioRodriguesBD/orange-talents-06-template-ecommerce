package br.com.zup.academy.mauricio.mercadolivre.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.security.core.AuthenticationException;

import br.com.zup.academy.mauricio.mercadolivre.request.TokenRequest;
import br.com.zup.academy.mauricio.mercadolivre.response.TokenResponse;
import br.com.zup.academy.mauricio.mercadolivre.security.TokenManager;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/token")
public class TokenController {

	@Autowired
	private TokenManager tokenManager;

	@Autowired
	private AuthenticationManager authManager;

	private static final Logger log = LoggerFactory.getLogger(TokenController.class);

	@PostMapping
	public ResponseEntity<TokenResponse> criaToken(@RequestBody TokenRequest request) {
		System.out.println(request);

		UsernamePasswordAuthenticationToken authenticationToken = request.geraOAutenticador();
		try {
			Authentication authentication = authManager.authenticate(authenticationToken);
			String token = tokenManager.generateToken(authentication);

			return ResponseEntity.ok(new TokenResponse(token));
		} catch (AuthenticationException e) {
			log.error("[Autenticacao] {}", e);
			return ResponseEntity.badRequest().build();
		}

	}
}
