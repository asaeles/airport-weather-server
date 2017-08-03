package com.crossover.aws.repository;

import com.crossover.aws.dao.Airport;
import com.crossover.aws.dao.AtmosphericInformation;
import java.util.Optional;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Simple Spring repository interface for Atmospheric Information
 *
 * @author asaeles@gmail.com
 */
public interface AtmosphericInformationRepository extends PagingAndSortingRepository<AtmosphericInformation, Long> {

    public Optional<AtmosphericInformation> findByAirport(Airport airport);

    // Spring is ingenious in translating method names into conditions
    public Long countByHasDataAndLastUpdateTimeGreaterThan(boolean hasData, long lastUpdateTime);
}
