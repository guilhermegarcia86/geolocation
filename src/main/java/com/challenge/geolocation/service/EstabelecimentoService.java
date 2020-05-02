package com.challenge.geolocation.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.challenge.geolocation.dto.EstabelecimentoDTO;
import com.challenge.geolocation.filter.Filter;
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

	public List<EstabelecimentoDTO> procuraEstabelecimentosProximoAMim(String endereco, String distance, String limit) {

		GeoApiContext context = new GeoApiContext.Builder().apiKey(apikey).build();

		GeocodingApiRequest request = GeocodingApi.newRequest(context).address(endereco);

		try {
			GeocodingResult[] results = request.await();
			GeocodingResult resultado = results[0];

			Geometry geometry = resultado.geometry;

			LatLng location = geometry.location;

			List<Estabelecimento> estabelecimentoList = repository.searchByGeolocation(
					Filter.toFilter(location.lat, location.lng, Double.valueOf(distance), Integer.valueOf(limit)));

			List<EstabelecimentoDTO> dtoList = estabelecimentoList.stream().map(estabelecimento -> {
				return EstabelecimentoDTO.toDTO(estabelecimento);
			}).collect(Collectors.toList());

			return dtoList;
		} catch (ApiException | InterruptedException | IOException e) {
			e.printStackTrace();
		}

		return List.of();
	}
}
