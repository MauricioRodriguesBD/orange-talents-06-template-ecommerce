package br.com.zup.academy.mauricio.mercadolivre.response;

import java.util.List;
import java.util.stream.Collectors;

import br.com.zup.academy.mauricio.mercadolivre.model.ImagemProduto;

public class ImagemResponse {

	private Long id;

	private String link;

	public ImagemResponse(ImagemProduto imagem) {
		this.id = imagem.getId();

		this.link = imagem.getLink();
	}
	

	public Long getId() {
		return id;
	}


	public String getLink() {
		return link;
	}


	public static List<ImagemResponse> converter(List<ImagemProduto> imagens) {
		return imagens.stream().map(i -> new ImagemResponse(i)).collect(Collectors.toList());
	}

}
