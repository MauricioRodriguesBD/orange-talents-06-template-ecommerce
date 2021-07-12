package br.com.zup.academy.mauricio.mercadolivre.request;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

public class ImagemRequest {

	@Size(min = 1)
	@NotNull
	private List<MultipartFile> imagem = new ArrayList<>();
	
	

 
//	public ImagemRequest(@Size(min = 1) @NotNull List<MultipartFile> imagem) {
//		super();
//		this.imagem = imagem;
//	}


	public void setImagem(List<MultipartFile> imagem) {
		this.imagem = imagem;
	}


	public List<MultipartFile> getImagem() {
		
		return imagem;
	}
	
	
	
}
