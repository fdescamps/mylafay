package com.fdescamps.mylafay.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.fdescamps.mylafay.domain.Repetition;
import com.fdescamps.mylafay.repository.RepetitionRepository;
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
 * REST controller for managing Repetition.
 */
@RestController
@RequestMapping("/app")
public class RepetitionResource {

    private final Logger log = LoggerFactory.getLogger(RepetitionResource.class);

    @Inject
    private RepetitionRepository repetitionRepository;

    /**
     * POST  /rest/repetitions -> Create a new repetition.
     */
    @RequestMapping(value = "/rest/repetitions",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void create(@RequestBody Repetition repetition) {
        log.debug("REST request to save Repetition : {}", repetition);
        repetitionRepository.save(repetition);
    }

    /**
     * GET  /rest/repetitions -> get all the repetitions.
     */
    @RequestMapping(value = "/rest/repetitions",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Repetition> getAll() {
        log.debug("REST request to get all Repetitions");
        return repetitionRepository.findAll();
    }

    /**
     * GET  /rest/repetitions/:id -> get the "id" repetition.
     */
    @RequestMapping(value = "/rest/repetitions/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Repetition> get(@PathVariable String id) {
        log.debug("REST request to get Repetition : {}", id);
        return Optional.ofNullable(repetitionRepository.findOne(id))
            .map(repetition -> new ResponseEntity<>(
                repetition,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /rest/repetitions/:id -> delete the "id" repetition.
     */
    @RequestMapping(value = "/rest/repetitions/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable String id) {
        log.debug("REST request to delete Repetition : {}", id);
        repetitionRepository.delete(id);
    }
}
