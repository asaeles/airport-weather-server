package com.crossover.aws.repository;

import com.crossover.aws.dao.Airport;
import java.util.Optional;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Simple Spring repository interface for Airports
 * 
 * @author asaeles@gmail.com
 */
@RepositoryRestResource(collectionResourceRel = "airports", path = "collect/airport")
public interface AirportRepository extends PagingAndSortingRepository<Airport, Long> {
	Optional<Airport> findByIata(String iata);
}
