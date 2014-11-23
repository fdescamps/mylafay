package com.fdescamps.mylafay.repository;

import com.fdescamps.mylafay.domain.Repetition;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the Repetition entity.
 */
public interface RepetitionRepository extends MongoRepository<Repetition, String> {

}
