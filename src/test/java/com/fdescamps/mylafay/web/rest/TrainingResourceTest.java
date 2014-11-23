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
import org.joda.time.LocalDate;
import java.util.List;

import com.fdescamps.mylafay.Application;
import com.fdescamps.mylafay.domain.Training;
import com.fdescamps.mylafay.repository.TrainingRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the TrainingResource REST controller.
 *
 * @see TrainingResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class TrainingResourceTest {

    private static final Integer DEFAULT_IDENTIFIER = 0;
    private static final Integer UPDATED_IDENTIFIER = 1;
    
    private static final Integer DEFAULT_TRAINING_NUMBER = 0;
    private static final Integer UPDATED_TRAINING_NUMBER = 1;
    
    private static final String DEFAULT_USER = "SAMPLE_TEXT";
    private static final String UPDATED_USER = "UPDATED_TEXT";
    
    private static final String DEFAULT_TYPE = "SAMPLE_TEXT";
    private static final String UPDATED_TYPE = "UPDATED_TEXT";
    
    private static final String DEFAULT_COMMENTARY = "SAMPLE_TEXT";
    private static final String UPDATED_COMMENTARY = "UPDATED_TEXT";
    
    private static final LocalDate DEFAULT_DATE = new LocalDate(0L);
    private static final LocalDate UPDATED_DATE = new LocalDate();
    
    private static final Integer DEFAULT_DURATION = 0;
    private static final Integer UPDATED_DURATION = 1;
    
    private static final String DEFAULT_WARMING = "SAMPLE_TEXT";
    private static final String UPDATED_WARMING = "UPDATED_TEXT";
    
    private static final String DEFAULT_STRETCHING = "SAMPLE_TEXT";
    private static final String UPDATED_STRETCHING = "UPDATED_TEXT";
    

    @Inject
    private TrainingRepository trainingRepository;

    private MockMvc restTrainingMockMvc;

    private Training training;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        TrainingResource trainingResource = new TrainingResource();
        ReflectionTestUtils.setField(trainingResource, "trainingRepository", trainingRepository);
        this.restTrainingMockMvc = MockMvcBuilders.standaloneSetup(trainingResource).build();
    }

    @Before
    public void initTest() {
        trainingRepository.deleteAll();
        training = new Training();
        training.setIdentifier(DEFAULT_IDENTIFIER);
        training.setTraining_number(DEFAULT_TRAINING_NUMBER);
        training.setUser(DEFAULT_USER);
        training.setType(DEFAULT_TYPE);
        training.setCommentary(DEFAULT_COMMENTARY);
        training.setDate(DEFAULT_DATE);
        training.setDuration(DEFAULT_DURATION);
        training.setWarming(DEFAULT_WARMING);
        training.setStretching(DEFAULT_STRETCHING);
    }

    @Test
    public void createTraining() throws Exception {
        // Validate the database is empty
        assertThat(trainingRepository.findAll()).hasSize(0);

        // Create the Training
        restTrainingMockMvc.perform(post("/app/rest/trainings")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(training)))
                .andExpect(status().isOk());

        // Validate the Training in the database
        List<Training> trainings = trainingRepository.findAll();
        assertThat(trainings).hasSize(1);
        Training testTraining = trainings.iterator().next();
        assertThat(testTraining.getIdentifier()).isEqualTo(DEFAULT_IDENTIFIER);
        assertThat(testTraining.getTraining_number()).isEqualTo(DEFAULT_TRAINING_NUMBER);
        assertThat(testTraining.getUser()).isEqualTo(DEFAULT_USER);
        assertThat(testTraining.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testTraining.getCommentary()).isEqualTo(DEFAULT_COMMENTARY);
        assertThat(testTraining.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testTraining.getDuration()).isEqualTo(DEFAULT_DURATION);
        assertThat(testTraining.getWarming()).isEqualTo(DEFAULT_WARMING);
        assertThat(testTraining.getStretching()).isEqualTo(DEFAULT_STRETCHING);
    }

    @Test
    public void getAllTrainings() throws Exception {
        // Initialize the database
        trainingRepository.save(training);

        // Get all the trainings
        restTrainingMockMvc.perform(get("/app/rest/trainings"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id").value(training.getId()))
                .andExpect(jsonPath("$.[0].identifier").value(DEFAULT_IDENTIFIER))
                .andExpect(jsonPath("$.[0].training_number").value(DEFAULT_TRAINING_NUMBER))
                .andExpect(jsonPath("$.[0].user").value(DEFAULT_USER.toString()))
                .andExpect(jsonPath("$.[0].type").value(DEFAULT_TYPE.toString()))
                .andExpect(jsonPath("$.[0].commentary").value(DEFAULT_COMMENTARY.toString()))
                .andExpect(jsonPath("$.[0].date").value(DEFAULT_DATE.toString()))
                .andExpect(jsonPath("$.[0].duration").value(DEFAULT_DURATION))
                .andExpect(jsonPath("$.[0].warming").value(DEFAULT_WARMING.toString()))
                .andExpect(jsonPath("$.[0].stretching").value(DEFAULT_STRETCHING.toString()));
    }

    @Test
    public void getTraining() throws Exception {
        // Initialize the database
        trainingRepository.save(training);

        // Get the training
        restTrainingMockMvc.perform(get("/app/rest/trainings/{id}", training.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(training.getId()))
            .andExpect(jsonPath("$.identifier").value(DEFAULT_IDENTIFIER))
            .andExpect(jsonPath("$.training_number").value(DEFAULT_TRAINING_NUMBER))
            .andExpect(jsonPath("$.user").value(DEFAULT_USER.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.commentary").value(DEFAULT_COMMENTARY.toString()))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.duration").value(DEFAULT_DURATION))
            .andExpect(jsonPath("$.warming").value(DEFAULT_WARMING.toString()))
            .andExpect(jsonPath("$.stretching").value(DEFAULT_STRETCHING.toString()));
    }

    @Test
    public void getNonExistingTraining() throws Exception {
        // Get the training
        restTrainingMockMvc.perform(get("/app/rest/trainings/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateTraining() throws Exception {
        // Initialize the database
        trainingRepository.save(training);

        // Update the training
        training.setIdentifier(UPDATED_IDENTIFIER);
        training.setTraining_number(UPDATED_TRAINING_NUMBER);
        training.setUser(UPDATED_USER);
        training.setType(UPDATED_TYPE);
        training.setCommentary(UPDATED_COMMENTARY);
        training.setDate(UPDATED_DATE);
        training.setDuration(UPDATED_DURATION);
        training.setWarming(UPDATED_WARMING);
        training.setStretching(UPDATED_STRETCHING);
        restTrainingMockMvc.perform(post("/app/rest/trainings")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(training)))
                .andExpect(status().isOk());

        // Validate the Training in the database
        List<Training> trainings = trainingRepository.findAll();
        assertThat(trainings).hasSize(1);
        Training testTraining = trainings.iterator().next();
        assertThat(testTraining.getIdentifier()).isEqualTo(UPDATED_IDENTIFIER);
        assertThat(testTraining.getTraining_number()).isEqualTo(UPDATED_TRAINING_NUMBER);
        assertThat(testTraining.getUser()).isEqualTo(UPDATED_USER);
        assertThat(testTraining.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testTraining.getCommentary()).isEqualTo(UPDATED_COMMENTARY);
        assertThat(testTraining.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testTraining.getDuration()).isEqualTo(UPDATED_DURATION);
        assertThat(testTraining.getWarming()).isEqualTo(UPDATED_WARMING);
        assertThat(testTraining.getStretching()).isEqualTo(UPDATED_STRETCHING);;
    }

    @Test
    public void deleteTraining() throws Exception {
        // Initialize the database
        trainingRepository.save(training);

        // Get the training
        restTrainingMockMvc.perform(delete("/app/rest/trainings/{id}", training.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Training> trainings = trainingRepository.findAll();
        assertThat(trainings).hasSize(0);
    }
}
