package com.challenge.geolocation.model;

import java.util.List;

import lombok.Data;

@Data
public class Location {
	
    private List<Double> location;
    
    private String type = "Point";

}
