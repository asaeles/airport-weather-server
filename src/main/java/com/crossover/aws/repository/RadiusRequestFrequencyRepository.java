package com.crossover.aws.repository;

import com.crossover.aws.dao.RadiusRequestFrequency;
import java.util.Optional;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Simple Spring repository interface for Radius Request Frequencies
 *
 * @author asaeles@gmail.com
 */
public interface RadiusRequestFrequencyRepository extends PagingAndSortingRepository<RadiusRequestFrequency, Long> {

    public Optional<RadiusRequestFrequency> findByRadius(double radius);
}
