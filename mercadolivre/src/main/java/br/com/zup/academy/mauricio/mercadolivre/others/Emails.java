package br.com.zup.academy.mauricio.mercadolivre.others;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zup.academy.mauricio.mercadolivre.model.Compra;
import br.com.zup.academy.mauricio.mercadolivre.model.PerguntaUsuario;

@Service
public class Emails {
	
	@Autowired 
	private Mailer mailer;

		public void novaPergunta(@NotNull @Valid PerguntaUsuario perguntaUsuario) {
			mailer.send("<html>...</html>","Nova pergunta...",perguntaUsuario.getUsuario().getEmail(),
					"novapergunta@nossomercadolivre.com",
					perguntaUsuario.getDonoProduto().getEmail());
		}
		
		 public void novaCompra(Compra novaCompra) {
		        mailer.send("nova compra..." + novaCompra, "Você tem uma nova compra",
		                novaCompra.getUsuario().getEmail(),
		                "compras@nossomercadolivre.com",
		                novaCompra.getDonoProduto().getEmail());
		    }
}
