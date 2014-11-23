package com.fdescamps.mylafay.repository;

import com.fdescamps.mylafay.domain.Exercise;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the Exercise entity.
 */
public interface ExerciseRepository extends MongoRepository<Exercise, String> {

}
