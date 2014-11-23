package com.fdescamps.mylafay.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.fdescamps.mylafay.domain.Training;
import com.fdescamps.mylafay.repository.TrainingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Training.
 */
@RestController
@RequestMapping("/app")
public class TrainingResource {

    private final Logger log = LoggerFactory.getLogger(TrainingResource.class);

    @Inject
    private TrainingRepository trainingRepository;

    /**
     * POST  /rest/trainings -> Create a new training.
     */
    @RequestMapping(value = "/rest/trainings",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void create(@RequestBody Training training) {
        log.debug("REST request to save Training : {}", training);
        trainingRepository.save(training);
    }

    /**
     * GET  /rest/trainings -> get all the trainings.
     */
    @RequestMapping(value = "/rest/trainings",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Training> getAll() {
        log.debug("REST request to get all Trainings");
        return trainingRepository.findAll();
    }

    /**
     * GET  /rest/trainings/:id -> get the "id" training.
     */
    @RequestMapping(value = "/rest/trainings/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Training> get(@PathVariable String id) {
        log.debug("REST request to get Training : {}", id);
        return Optional.ofNullable(trainingRepository.findOne(id))
            .map(training -> new ResponseEntity<>(
                training,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /rest/trainings/:id -> delete the "id" training.
     */
    @RequestMapping(value = "/rest/trainings/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable String id) {
        log.debug("REST request to delete Training : {}", id);
        trainingRepository.delete(id);
    }
}
