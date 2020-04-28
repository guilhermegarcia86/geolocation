package com.challenge.geolocation.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Service;

import com.challenge.geolocation.model.Estabelecimento;
import com.challenge.geolocation.repository.EstabelecimentoRepository;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.GeocodingApiRequest;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.Geometry;
import com.google.maps.model.LatLng;

@Service
public class EstabelecimentoService {

	@Autowired
	private EstabelecimentoRepository repository;

	@Value("${apikey}")
	private String apikey;

	public List<Estabelecimento> procuraEstabelecimentosProximoAMim(String endereco)
			throws ApiException, InterruptedException, IOException {

		GeoApiContext context = new GeoApiContext.Builder().apiKey(apikey).build();

		GeocodingApiRequest request = GeocodingApi.newRequest(context).address(endereco);

		GeocodingResult[] results = request.await();

		GeocodingResult resultado = results[0];

		Geometry geometry = resultado.geometry;

		LatLng location = geometry.location;

		return repository.findByLocationWithin(new GeoJsonPoint(new Point(location.lat, location.lng)));

	}

}
