package com.challenge.geolocation.filter;

import lombok.Getter;

@Getter
public class Filter {
	
	private Filter() {}
	
	private double lat;
	private double lng;
	private double distance;
	private int limit;
	
	public static Filter toFilter(double latitude, double longitude, double distance, int limit) {
		Filter filter = new Filter();
		filter.lat = latitude;
		filter.lng = longitude;
		filter.distance = distance == 0 ? 1000.0 : distance;
		filter.limit = limit == 0 ? 10 : limit;		
		
		return filter;
	}

}
