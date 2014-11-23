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
import com.fdescamps.mylafay.domain.Repetition;
import com.fdescamps.mylafay.repository.RepetitionRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the RepetitionResource REST controller.
 *
 * @see RepetitionResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class RepetitionResourceTest {

    private static final Integer DEFAULT_SET_IDENTIFIER = 0;
    private static final Integer UPDATED_SET_IDENTIFIER = 1;
    
    private static final Integer DEFAULT_TRAINING_IDENTIFIER = 0;
    private static final Integer UPDATED_TRAINING_IDENTIFIER = 1;
    
    private static final String DEFAULT_EXERCICE_IDENTIFIER = "SAMPLE_TEXT";
    private static final String UPDATED_EXERCICE_IDENTIFIER = "UPDATED_TEXT";
    
    private static final Integer DEFAULT_NB_OF_REPETITIONS = 0;
    private static final Integer UPDATED_NB_OF_REPETITIONS = 1;
    

    @Inject
    private RepetitionRepository repetitionRepository;

    private MockMvc restRepetitionMockMvc;

    private Repetition repetition;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        RepetitionResource repetitionResource = new RepetitionResource();
        ReflectionTestUtils.setField(repetitionResource, "repetitionRepository", repetitionRepository);
        this.restRepetitionMockMvc = MockMvcBuilders.standaloneSetup(repetitionResource).build();
    }

    @Before
    public void initTest() {
        repetitionRepository.deleteAll();
        repetition = new Repetition();
        repetition.setSet_identifier(DEFAULT_SET_IDENTIFIER);
        repetition.setTraining_identifier(DEFAULT_TRAINING_IDENTIFIER);
        repetition.setExercice_identifier(DEFAULT_EXERCICE_IDENTIFIER);
        repetition.setNb_of_repetitions(DEFAULT_NB_OF_REPETITIONS);
    }

    @Test
    public void createRepetition() throws Exception {
        // Validate the database is empty
        assertThat(repetitionRepository.findAll()).hasSize(0);

        // Create the Repetition
        restRepetitionMockMvc.perform(post("/app/rest/repetitions")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(repetition)))
                .andExpect(status().isOk());

        // Validate the Repetition in the database
        List<Repetition> repetitions = repetitionRepository.findAll();
        assertThat(repetitions).hasSize(1);
        Repetition testRepetition = repetitions.iterator().next();
        assertThat(testRepetition.getSet_identifier()).isEqualTo(DEFAULT_SET_IDENTIFIER);
        assertThat(testRepetition.getTraining_identifier()).isEqualTo(DEFAULT_TRAINING_IDENTIFIER);
        assertThat(testRepetition.getExercice_identifier()).isEqualTo(DEFAULT_EXERCICE_IDENTIFIER);
        assertThat(testRepetition.getNb_of_repetitions()).isEqualTo(DEFAULT_NB_OF_REPETITIONS);
    }

    @Test
    public void getAllRepetitions() throws Exception {
        // Initialize the database
        repetitionRepository.save(repetition);

        // Get all the repetitions
        restRepetitionMockMvc.perform(get("/app/rest/repetitions"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id").value(repetition.getId()))
                .andExpect(jsonPath("$.[0].set_identifier").value(DEFAULT_SET_IDENTIFIER))
                .andExpect(jsonPath("$.[0].training_identifier").value(DEFAULT_TRAINING_IDENTIFIER))
                .andExpect(jsonPath("$.[0].exercice_identifier").value(DEFAULT_EXERCICE_IDENTIFIER.toString()))
                .andExpect(jsonPath("$.[0].nb_of_repetitions").value(DEFAULT_NB_OF_REPETITIONS));
    }

    @Test
    public void getRepetition() throws Exception {
        // Initialize the database
        repetitionRepository.save(repetition);

        // Get the repetition
        restRepetitionMockMvc.perform(get("/app/rest/repetitions/{id}", repetition.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(repetition.getId()))
            .andExpect(jsonPath("$.set_identifier").value(DEFAULT_SET_IDENTIFIER))
            .andExpect(jsonPath("$.training_identifier").value(DEFAULT_TRAINING_IDENTIFIER))
            .andExpect(jsonPath("$.exercice_identifier").value(DEFAULT_EXERCICE_IDENTIFIER.toString()))
            .andExpect(jsonPath("$.nb_of_repetitions").value(DEFAULT_NB_OF_REPETITIONS));
    }

    @Test
    public void getNonExistingRepetition() throws Exception {
        // Get the repetition
        restRepetitionMockMvc.perform(get("/app/rest/repetitions/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateRepetition() throws Exception {
        // Initialize the database
        repetitionRepository.save(repetition);

        // Update the repetition
        repetition.setSet_identifier(UPDATED_SET_IDENTIFIER);
        repetition.setTraining_identifier(UPDATED_TRAINING_IDENTIFIER);
        repetition.setExercice_identifier(UPDATED_EXERCICE_IDENTIFIER);
        repetition.setNb_of_repetitions(UPDATED_NB_OF_REPETITIONS);
        restRepetitionMockMvc.perform(post("/app/rest/repetitions")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(repetition)))
                .andExpect(status().isOk());

        // Validate the Repetition in the database
        List<Repetition> repetitions = repetitionRepository.findAll();
        assertThat(repetitions).hasSize(1);
        Repetition testRepetition = repetitions.iterator().next();
        assertThat(testRepetition.getSet_identifier()).isEqualTo(UPDATED_SET_IDENTIFIER);
        assertThat(testRepetition.getTraining_identifier()).isEqualTo(UPDATED_TRAINING_IDENTIFIER);
        assertThat(testRepetition.getExercice_identifier()).isEqualTo(UPDATED_EXERCICE_IDENTIFIER);
        assertThat(testRepetition.getNb_of_repetitions()).isEqualTo(UPDATED_NB_OF_REPETITIONS);;
    }

    @Test
    public void deleteRepetition() throws Exception {
        // Initialize the database
        repetitionRepository.save(repetition);

        // Get the repetition
        restRepetitionMockMvc.perform(delete("/app/rest/repetitions/{id}", repetition.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Repetition> repetitions = repetitionRepository.findAll();
        assertThat(repetitions).hasSize(0);
    }
}
