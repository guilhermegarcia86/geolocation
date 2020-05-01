package com.challenge.geolocation.model;

import org.bson.types.ObjectId;

import lombok.Data;

@Data
public class Estabelecimento{

	private ObjectId id;

	private String nome;
	private String email;
	private Localizacao localizacao;	
	
	public Estabelecimento generateId() {
		this.setId(new ObjectId());
		return this;
	}
	
}
