package com.fdescamps.mylafay.web.rest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;

import com.fdescamps.mylafay.Application;
import com.fdescamps.mylafay.domain.Execution;
import com.fdescamps.mylafay.repository.ExecutionRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ExecutionResource REST controller.
 *
 * @see ExecutionResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class ExecutionResourceTest {

    private static final Integer DEFAULT_TRAINING_IDENTIFIER = 0;
    private static final Integer UPDATED_TRAINING_IDENTIFIER = 1;
    
    private static final String DEFAULT_EXERCICE_IDENTIFIER = "SAMPLE_TEXT";
    private static final String UPDATED_EXERCICE_IDENTIFIER = "UPDATED_TEXT";
    
    private static final Integer DEFAULT_SET = 0;
    private static final Integer UPDATED_SET = 1;
    
    private static final String DEFAULT_COMMENTARY = "SAMPLE_TEXT";
    private static final String UPDATED_COMMENTARY = "UPDATED_TEXT";
    
    private static final String DEFAULT_DIFFICULTY = "SAMPLE_TEXT";
    private static final String UPDATED_DIFFICULTY = "UPDATED_TEXT";
    

    @Inject
    private ExecutionRepository executionRepository;

    private MockMvc restExecutionMockMvc;

    private Execution execution;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ExecutionResource executionResource = new ExecutionResource();
        ReflectionTestUtils.setField(executionResource, "executionRepository", executionRepository);
        this.restExecutionMockMvc = MockMvcBuilders.standaloneSetup(executionResource).build();
    }

    @Before
    public void initTest() {
        executionRepository.deleteAll();
        execution = new Execution();
        execution.setTraining_identifier(DEFAULT_TRAINING_IDENTIFIER);
        execution.setExercice_identifier(DEFAULT_EXERCICE_IDENTIFIER);
        execution.setSet(DEFAULT_SET);
        execution.setCommentary(DEFAULT_COMMENTARY);
        execution.setDifficulty(DEFAULT_DIFFICULTY);
    }

    @Test
    public void createExecution() throws Exception {
        // Validate the database is empty
        assertThat(executionRepository.findAll()).hasSize(0);

        // Create the Execution
        restExecutionMockMvc.perform(post("/app/rest/executions")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(execution)))
                .andExpect(status().isOk());

        // Validate the Execution in the database
        List<Execution> executions = executionRepository.findAll();
        assertThat(executions).hasSize(1);
        Execution testExecution = executions.iterator().next();
        assertThat(testExecution.getTraining_identifier()).isEqualTo(DEFAULT_TRAINING_IDENTIFIER);
        assertThat(testExecution.getExercice_identifier()).isEqualTo(DEFAULT_EXERCICE_IDENTIFIER);
        assertThat(testExecution.getSet()).isEqualTo(DEFAULT_SET);
        assertThat(testExecution.getCommentary()).isEqualTo(DEFAULT_COMMENTARY);
        assertThat(testExecution.getDifficulty()).isEqualTo(DEFAULT_DIFFICULTY);
    }

    @Test
    public void getAllExecutions() throws Exception {
        // Initialize the database
        executionRepository.save(execution);

        // Get all the executions
        restExecutionMockMvc.perform(get("/app/rest/executions"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id").value(execution.getId()))
                .andExpect(jsonPath("$.[0].training_identifier").value(DEFAULT_TRAINING_IDENTIFIER))
                .andExpect(jsonPath("$.[0].exercice_identifier").value(DEFAULT_EXERCICE_IDENTIFIER.toString()))
                .andExpect(jsonPath("$.[0].set").value(DEFAULT_SET))
                .andExpect(jsonPath("$.[0].commentary").value(DEFAULT_COMMENTARY.toString()))
                .andExpect(jsonPath("$.[0].difficulty").value(DEFAULT_DIFFICULTY.toString()));
    }

    @Test
    public void getExecution() throws Exception {
        // Initialize the database
        executionRepository.save(execution);

        // Get the execution
        restExecutionMockMvc.perform(get("/app/rest/executions/{id}", execution.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(execution.getId()))
            .andExpect(jsonPath("$.training_identifier").value(DEFAULT_TRAINING_IDENTIFIER))
            .andExpect(jsonPath("$.exercice_identifier").value(DEFAULT_EXERCICE_IDENTIFIER.toString()))
            .andExpect(jsonPath("$.set").value(DEFAULT_SET))
            .andExpect(jsonPath("$.commentary").value(DEFAULT_COMMENTARY.toString()))
            .andExpect(jsonPath("$.difficulty").value(DEFAULT_DIFFICULTY.toString()));
    }

    @Test
    public void getNonExistingExecution() throws Exception {
        // Get the execution
        restExecutionMockMvc.perform(get("/app/rest/executions/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateExecution() throws Exception {
        // Initialize the database
        executionRepository.save(execution);

        // Update the execution
        execution.setTraining_identifier(UPDATED_TRAINING_IDENTIFIER);
        execution.setExercice_identifier(UPDATED_EXERCICE_IDENTIFIER);
        execution.setSet(UPDATED_SET);
        execution.setCommentary(UPDATED_COMMENTARY);
        execution.setDifficulty(UPDATED_DIFFICULTY);
        restExecutionMockMvc.perform(post("/app/rest/executions")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(execution)))
                .andExpect(status().isOk());

        // Validate the Execution in the database
        List<Execution> executions = executionRepository.findAll();
        assertThat(executions).hasSize(1);
        Execution testExecution = executions.iterator().next();
        assertThat(testExecution.getTraining_identifier()).isEqualTo(UPDATED_TRAINING_IDENTIFIER);
        assertThat(testExecution.getExercice_identifier()).isEqualTo(UPDATED_EXERCICE_IDENTIFIER);
        assertThat(testExecution.getSet()).isEqualTo(UPDATED_SET);
        assertThat(testExecution.getCommentary()).isEqualTo(UPDATED_COMMENTARY);
        assertThat(testExecution.getDifficulty()).isEqualTo(UPDATED_DIFFICULTY);;
    }

    @Test
    public void deleteExecution() throws Exception {
        // Initialize the database
        executionRepository.save(execution);

        // Get the execution
        restExecutionMockMvc.perform(delete("/app/rest/executions/{id}", execution.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Execution> executions = executionRepository.findAll();
        assertThat(executions).hasSize(0);
    }
}
