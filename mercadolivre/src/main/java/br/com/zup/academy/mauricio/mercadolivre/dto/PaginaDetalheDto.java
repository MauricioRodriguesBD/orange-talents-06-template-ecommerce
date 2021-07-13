package br.com.zup.academy.mauricio.mercadolivre.dto;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

import br.com.zup.academy.mauricio.mercadolivre.model.Produto;
import br.com.zup.academy.mauricio.mercadolivre.others.DetalheProdutoCaracteristica;
import br.com.zup.academy.mauricio.mercadolivre.others.Opinioes;


public class PaginaDetalheDto {
		
	private String descricao;
	
	private String nome;
	
	private BigDecimal valor;
	
	private Set<DetalheProdutoCaracteristica> caracteristicas;
	
	private Set<String> imagem;
	
	private SortedSet<String> pergunta;
	
	private Set<Map<String,String>> opiniao;
	
	private double mediaNotas;
	
	private int total;

	public PaginaDetalheDto(Produto produto) {
		super();
		this.descricao = produto.getDescricao();
		this.nome = produto.getNome();
		this.valor = produto.getValor();
		this.caracteristicas = produto
				.mapeiaCaracteristicas(DetalheProdutoCaracteristica::new);
		this.imagem = produto.mapeiaImagens(imagem -> imagem.getLink());
		this.pergunta = produto.mapeiaPerguntas(pergunta -> pergunta.getTitulo());
		
		Opinioes opinioes = produto.getOpiniao();
		
		this.opiniao = opinioes.mapeiaOpinioes(opiniao -> {
			return Map.of("titulo",opiniao.getTitulo(),"descricao",opiniao.getDescricao());
		});
		this.mediaNotas = opinioes.media();
		this.total = opinioes.total();
	}

	public String getDescricao() {
		return descricao;
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public Set<DetalheProdutoCaracteristica> getCaracteristicas() {
		return caracteristicas;
	}

	public Set<String> getImagem() {
		return imagem;
	}

	public SortedSet<String> getPergunta() {
		return pergunta;
	}

	public Set<Map<String, String>> getOpiniao() {
		return opiniao;
	}

	public double getMediaNotas() {
		return mediaNotas;
	}

	public int getTotal() {
		return total;
	}
	
	
	
	
	
	
	
	
}
