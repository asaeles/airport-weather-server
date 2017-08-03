package com.crossover.aws.repository;

import com.crossover.aws.dao.Airport;
import com.crossover.aws.dao.AirportRequestFrequency;
import java.util.Optional;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Simple Spring repository interface for Airport Request Frequencies
 * 
 * @author asaeles@gmail.com
 */
public interface AirportRequestFrequencyRepository extends PagingAndSortingRepository<AirportRequestFrequency, Long> {

    public Optional<AirportRequestFrequency> findByAirport(Airport airport);
}
