package com.challenge.geolocation.dto;

import com.challenge.geolocation.model.Estabelecimento;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

import lombok.Getter;

@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@Getter
public class EstabelecimentoDTO {
	
	private EstabelecimentoDTO() {}
	
	private String nome;
	private String email;
	private String endereco;

	public static EstabelecimentoDTO toDTO(Estabelecimento estabelecimento) {
		EstabelecimentoDTO dto = new EstabelecimentoDTO();
		
		dto.nome = estabelecimento.getNome();
		dto.email = estabelecimento.getEmail();
		dto.endereco = estabelecimento.getLocalizacao().getEndereco();		
		
		return dto;
	}
}
