package com.challenge.geolocation.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "estabelecimento")
public class Estabelecimento {
	
	@Id
	private String id;

	private String nome;
	private String email;
	private String endereco;
	
    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2D)
    private Location location;
	
}
