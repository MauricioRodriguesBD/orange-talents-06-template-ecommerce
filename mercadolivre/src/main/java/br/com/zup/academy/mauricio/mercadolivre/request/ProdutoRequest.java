	package br.com.zup.academy.mauricio.mercadolivre.request;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import br.com.zup.academy.mauricio.mercadolivre.model.Categoria;
import br.com.zup.academy.mauricio.mercadolivre.model.Produto;
import br.com.zup.academy.mauricio.mercadolivre.model.Usuario;
import br.com.zup.academy.mauricio.mercadolivre.validation.ExistsId;

public class ProdutoRequest {

	@NotBlank
	private String nome;

	@Positive
	private BigDecimal valor;

	@NotNull
	@Positive
	private Integer quantidade;

	@Size(max = 1000)
	@NotBlank
	private String descricao;

	@ExistsId(domainClass = Categoria.class, fieldName = "id")
	@NotNull
	private Long idCategoria;

	@Size(min = 3)
	@Valid
	private List<NovaCaracteristicaRequest> caracteristicas = new ArrayList<>();

	public ProdutoRequest(@NotBlank String nome, @Positive BigDecimal valor, @NotNull @Positive Integer quantidade,
			@Size(max = 1000) @NotBlank String descricao, @NotNull Long idCategoria,
			@Size(min = 3) @Valid List<NovaCaracteristicaRequest> caracteristicas) {
		super();
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.idCategoria = idCategoria;
		this.caracteristicas.addAll(caracteristicas);
	}

	

	public List<NovaCaracteristicaRequest> getCaracteristicas() {
		return caracteristicas;
	}

	public void setCaracteristicas(List<NovaCaracteristicaRequest> caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

	public Produto toModel(EntityManager manager, Usuario dono) {
		@NotNull
		Categoria categoria = manager.find(Categoria.class, idCategoria);

		return new Produto(this.nome, this.valor, this.quantidade, this.descricao, categoria,
				caracteristicas,dono);

	}

	public Set<String> buscaCaracteristicasIguais() {
		HashSet<String> nomesIguais = new HashSet<>();
		HashSet<String> resultados = new HashSet<>();

		for (NovaCaracteristicaRequest caracteristica : caracteristicas) {
			String nome = caracteristica.getNome();

			if (!nomesIguais.add(nome)) {
				resultados.add(nome);
			}
		}
		return resultados;
	}
}
