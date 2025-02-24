package br.com.zup.academy.mauricio.mercadolivre.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.zup.academy.mauricio.mercadolivre.others.Opinioes;
import br.com.zup.academy.mauricio.mercadolivre.request.NovaCaracteristicaRequest;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	@Positive
	private BigDecimal valor;

	private Integer quantidade;

	@Size(max = 1000)
	private String descricao;

	@ManyToOne
	@Valid
	@NotNull
	private Categoria categoria;

	@OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
	private Set<CaracteristicaProduto> caracteristicas = new HashSet<>();

	@OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
	@JsonIgnore
	private Set<ImagemProduto> imagem = new HashSet<>();
	
	@OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
	private Set<PerguntaUsuario> pergunta = new HashSet<>();
	
	@OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
	private Set<Opiniao> opiniao = new HashSet<>();
	
	

	@NotNull
	@ManyToOne
	private Usuario dono;

	@NotNull
	private LocalDate data = LocalDate.now();

	@Deprecated
	private Produto() {
	}

	public Produto(String nome, @Positive BigDecimal valor, Integer quantidade, @Size(max = 1000) String descricao,
			Categoria categoria, Collection<NovaCaracteristicaRequest> caracteristicas, @NotNull Usuario dono) {
		super();
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.categoria = categoria;
		this.caracteristicas.addAll(caracteristicas.stream().map(caracteristica -> caracteristica.toModel(this))
				.collect(Collectors.toSet()));
		this.dono = dono;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	public <T> Set<T> mapeiaCaracteristicas(Function<CaracteristicaProduto, T> funcaoMapeadora) {
		return this.caracteristicas.stream().map(funcaoMapeadora).collect(Collectors.toSet());
	}

	public void associaImagem(Set<String> links) {
		Set<ImagemProduto> imagem = links.stream().map(link -> new ImagemProduto(this, link))
				.collect(Collectors.toSet());

		this.imagem.addAll(imagem);
	}

	public String getNome() {
		return nome;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public Usuario getDono() {
		return dono;
	}
	

	public BigDecimal getValor() {
		return valor;
	}
	
		
	public Opinioes getOpiniao() {
		return new Opinioes(this.opiniao);
	}

	public Set<ImagemProduto> getImagem() {
		return imagem;
	}

	public Set<CaracteristicaProduto> getCaracteristicas() {
		return caracteristicas;
	}

	public void setCaracteristicas(Set<CaracteristicaProduto> caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

	public <T> Set<T> mapeiaImagens(Function<ImagemProduto, T> funcaoMapeadora) {
		return this.imagem.stream().map(funcaoMapeadora)
				.collect(Collectors.toSet());
	}
	
	public <T extends Comparable<T>> SortedSet<T> mapeiaPerguntas(Function<PerguntaUsuario, T> funcaoMapeadora) {
		return this.pergunta.stream().map(funcaoMapeadora)
				.collect(Collectors.toCollection(TreeSet :: new));
	}

	public boolean abataEstoque(@Positive int quantidade) {
		Assert.isTrue(quantidade > 0, "A quantidade deve ser maior que zero para abater o estoque "+quantidade);
		
		if(quantidade <= this.quantidade) {
			this.quantidade-=quantidade;
			return true;
			
		}
		
		return false;
	}

	
	

}
