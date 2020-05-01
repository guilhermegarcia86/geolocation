package com.challenge.geolocation;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.challenge.geolocation.model.Estabelecimento;
import com.challenge.geolocation.model.Localizacao;
import com.challenge.geolocation.repository.EstabelecimentoRepository;

@SpringBootApplication
public class GeolocationApplication /*implements CommandLineRunner*/{

	public static void main(String[] args) {
		SpringApplication.run(GeolocationApplication.class, args);
	}
	
	@Autowired
	private EstabelecimentoRepository repository;

//	@Override
//	public void run(String... args) throws Exception {
//		
//		Estabelecimento estI = new Estabelecimento();
//		estI.setNome("Mercado I");
//		estI.setEmail("contato@mercadoI.com");
//		
//		Localizacao loc = new Localizacao();
//		loc.setEndereco("Rua Brigadeiro Tobias n 780");
//		loc.setType("Point");
//		loc.setCoordinates(Arrays.asList(-23.53624, -46.63395));
//		
//		estI.setLocalizacao(loc);
//		
//		Estabelecimento estII = new Estabelecimento();
//		estII.setEmail("contato@mercadoII.com");
//		estII.setNome("Estabelecimento II");
//		
//		Localizacao localizacaoII = new Localizacao();
//		localizacaoII.setEndereco("R. Brg. Tobias, 206 - Santa Ifigênia, São Paulo - SP, 01032-000");
//		localizacaoII.setType("Point");
//		localizacaoII.setCoordinates(Arrays.asList(-23.54165, -46.63583));
//		
//		estII.setLocalizacao(localizacaoII);
//		
//		Estabelecimento estIII = new Estabelecimento();
//		estIII.setEmail("contato@mercadoIII.com");
//		estIII.setNome("Estabelecimento III");
//		
//		Localizacao localizacaoIII = new Localizacao();
//		localizacaoIII.setType("Point");
//		localizacaoIII.setEndereco("Av. Cásper Líbero, 42 - Centro Histórico De São Paulo, São Paulo - SP, 01033-000");
//		localizacaoIII.setCoordinates(Arrays.asList(-23.54132, -46.63643));
//		
//		estIII.setLocalizacao(localizacaoIII);
//		
//		
//		Estabelecimento estIV = new Estabelecimento();
//		estIV.setEmail("contato@mercadoIV.com");
//		estIV.setNome("Estabelecimento IV");
//		
//		Localizacao localizacaoIV = new Localizacao();
//		localizacaoIV.setType("Point");
//		localizacaoIV.setEndereco("Av. Rio Branco, 630 - República, São Paulo - SP, 01205-000");
//		localizacaoIV.setCoordinates(Arrays.asList(-23.53984, -46.64008));
//		
//		estIV.setLocalizacao(localizacaoIV);
//		
//		Estabelecimento estV = new Estabelecimento();
//		estV.setEmail("contato@mercadoV.com");
//		estV.setNome("Estabelecimento V");
//		
//		Localizacao localizacaoV = new Localizacao();
//		localizacaoV.setType("Point");
//		localizacaoV.setEndereco("Alameda Barão de Limeira, 425 - Campos Elíseos, São Paulo - SP, 01202-900");
//		localizacaoV.setCoordinates(Arrays.asList(-23.53386, -46.6482));
//		
//		estV.setLocalizacao(localizacaoV);
//		
//		Estabelecimento estVI = new Estabelecimento();
//		estVI.setEmail("contato@mercadoVI.com");
//		estVI.setNome("Estabelecimento VI");
//		
//		Localizacao localizacaoVI = new Localizacao();
//		localizacaoVI.setType("Point");
//		localizacaoVI.setEndereco("R. Canuto do Val, 41 - Santa Cecilia, São Paulo - SP, 01224-040");
//		localizacaoVI.setCoordinates(Arrays.asList(-23.54062, -46.65114));
//		
//		estVI.setLocalizacao(localizacaoVI);
//		
//		
//		Arrays.asList(estI, estII, estIII, estIV, estV, estVI).forEach(repository::save);
//		
//		
//	}
}
