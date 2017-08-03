package com.crossover.aws.repository;

import com.crossover.aws.dao.DataPoint;
import org.springframework.data.repository.CrudRepository;

/**
 * Simple Spring repository interface for Data Points
 *
 * @author asaeles@gmail.com
 */
public interface DataPointRepository extends CrudRepository<DataPoint, Long> {
}
