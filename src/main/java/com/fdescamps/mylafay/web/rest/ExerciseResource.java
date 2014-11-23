package com.fdescamps.mylafay.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.fdescamps.mylafay.domain.Exercise;
import com.fdescamps.mylafay.repository.ExerciseRepository;
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
 * REST controller for managing Exercise.
 */
@RestController
@RequestMapping("/app")
public class ExerciseResource {

    private final Logger log = LoggerFactory.getLogger(ExerciseResource.class);

    @Inject
    private ExerciseRepository exerciseRepository;

    /**
     * POST  /rest/exercises -> Create a new exercise.
     */
    @RequestMapping(value = "/rest/exercises",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void create(@RequestBody Exercise exercise) {
        log.debug("REST request to save Exercise : {}", exercise);
        exerciseRepository.save(exercise);
    }

    /**
     * GET  /rest/exercises -> get all the exercises.
     */
    @RequestMapping(value = "/rest/exercises",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Exercise> getAll() {
        log.debug("REST request to get all Exercises");
        return exerciseRepository.findAll();
    }

    /**
     * GET  /rest/exercises/:id -> get the "id" exercise.
     */
    @RequestMapping(value = "/rest/exercises/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Exercise> get(@PathVariable String id) {
        log.debug("REST request to get Exercise : {}", id);
        return Optional.ofNullable(exerciseRepository.findOne(id))
            .map(exercise -> new ResponseEntity<>(
                exercise,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /rest/exercises/:id -> delete the "id" exercise.
     */
    @RequestMapping(value = "/rest/exercises/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable String id) {
        log.debug("REST request to delete Exercise : {}", id);
        exerciseRepository.delete(id);
    }
}
