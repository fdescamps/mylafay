package com.fdescamps.mylafay.repository;

import com.fdescamps.mylafay.domain.Execution;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the Execution entity.
 */
public interface ExecutionRepository extends MongoRepository<Execution, String> {

}
