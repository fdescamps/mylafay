package com.fdescamps.mylafay.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.fdescamps.mylafay.domain.Execution;
import com.fdescamps.mylafay.repository.ExecutionRepository;
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
 * REST controller for managing Execution.
 */
@RestController
@RequestMapping("/app")
public class ExecutionResource {

    private final Logger log = LoggerFactory.getLogger(ExecutionResource.class);

    @Inject
    private ExecutionRepository executionRepository;

    /**
     * POST  /rest/executions -> Create a new execution.
     */
    @RequestMapping(value = "/rest/executions",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void create(@RequestBody Execution execution) {
        log.debug("REST request to save Execution : {}", execution);
        executionRepository.save(execution);
    }

    /**
     * GET  /rest/executions -> get all the executions.
     */
    @RequestMapping(value = "/rest/executions",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Execution> getAll() {
        log.debug("REST request to get all Executions");
        return executionRepository.findAll();
    }

    /**
     * GET  /rest/executions/:id -> get the "id" execution.
     */
    @RequestMapping(value = "/rest/executions/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Execution> get(@PathVariable String id) {
        log.debug("REST request to get Execution : {}", id);
        return Optional.ofNullable(executionRepository.findOne(id))
            .map(execution -> new ResponseEntity<>(
                execution,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /rest/executions/:id -> delete the "id" execution.
     */
    @RequestMapping(value = "/rest/executions/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable String id) {
        log.debug("REST request to delete Execution : {}", id);
        executionRepository.delete(id);
    }
}
