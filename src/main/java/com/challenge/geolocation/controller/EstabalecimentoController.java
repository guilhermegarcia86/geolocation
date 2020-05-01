package com.challenge.geolocation.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.geolocation.dto.EstabelecimentoDTO;
import com.challenge.geolocation.model.Estabelecimento;
import com.challenge.geolocation.repository.EstabelecimentoRepository;
import com.challenge.geolocation.service.EstabelecimentoService;
import com.google.maps.errors.ApiException;

@RestController
@RequestMapping("api")
public class EstabalecimentoController {

	@Autowired
	private EstabelecimentoService service;

	@Autowired
	private EstabelecimentoRepository repository;

	@GetMapping("all")
	public List<Estabelecimento> findAll() {
		return repository.findAll();
	}

	@GetMapping("estabelecimento")
	public List<EstabelecimentoDTO> pegaEstabelecimentosProximosPeloEndereco(
			@RequestParam(name = "limit", defaultValue = "10") String limit,
			@RequestParam(name = "distancia", defaultValue = "1000.00") String distancia,
			@RequestParam("endereco") String endereco) throws ApiException, InterruptedException, IOException {
		return service.procuraEstabelecimentosProximoAMim(endereco, distancia, limit);
	}

}
