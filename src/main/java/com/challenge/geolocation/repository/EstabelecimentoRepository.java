package com.challenge.geolocation.repository;

import java.util.List;

import org.springframework.data.geo.Point;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.challenge.geolocation.model.Estabelecimento;

@Repository
public interface EstabelecimentoRepository extends CrudRepository<Estabelecimento, String> {
	
	List<Estabelecimento> findByLocationWithin(Point location);
	
}
