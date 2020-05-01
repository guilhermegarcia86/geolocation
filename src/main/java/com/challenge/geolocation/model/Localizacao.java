package com.challenge.geolocation.model;

import java.util.List;

import lombok.Data;

@Data
public class Localizacao {
	
	private String endereco;
    private List<Double> coordinates;    
    private String type = "Point";

}
