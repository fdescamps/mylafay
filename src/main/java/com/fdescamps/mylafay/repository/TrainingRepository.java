package com.fdescamps.mylafay.repository;

import com.fdescamps.mylafay.domain.Training;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the Training entity.
 */
public interface TrainingRepository extends MongoRepository<Training, String> {

}
