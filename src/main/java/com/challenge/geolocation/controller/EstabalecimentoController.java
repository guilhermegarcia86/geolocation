package com.challenge.geolocation.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public List<Estabelecimento> findAll(){
		Iterable<Estabelecimento> iterable = repository.findAll();
		
		List<Estabelecimento> estabelecimentos = new ArrayList<>();
		
		iterable.forEach(estabelecimentos::add);
		
		return estabelecimentos;
	}

	@GetMapping("estabelecimento")
	public List<Estabelecimento> pegaEstabelecimentosProximosPeloEndereco(@RequestParam("endereco") String endereco)
			throws ApiException, InterruptedException, IOException {
		return service.procuraEstabelecimentosProximoAMim(endereco);
	}

}
