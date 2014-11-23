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
import com.fdescamps.mylafay.domain.Exercise;
import com.fdescamps.mylafay.repository.ExerciseRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ExerciseResource REST controller.
 *
 * @see ExerciseResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class ExerciseResourceTest {

    private static final String DEFAULT_IDENTIFIER = "SAMPLE_TEXT";
    private static final String UPDATED_IDENTIFIER = "UPDATED_TEXT";
    
    private static final String DEFAULT_TYPE = "SAMPLE_TEXT";
    private static final String UPDATED_TYPE = "UPDATED_TEXT";
    
    private static final String DEFAULT_NAME = "SAMPLE_TEXT";
    private static final String UPDATED_NAME = "UPDATED_TEXT";
    
    private static final String DEFAULT_COMMENTARY = "SAMPLE_TEXT";
    private static final String UPDATED_COMMENTARY = "UPDATED_TEXT";
    

    @Inject
    private ExerciseRepository exerciseRepository;

    private MockMvc restExerciseMockMvc;

    private Exercise exercise;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ExerciseResource exerciseResource = new ExerciseResource();
        ReflectionTestUtils.setField(exerciseResource, "exerciseRepository", exerciseRepository);
        this.restExerciseMockMvc = MockMvcBuilders.standaloneSetup(exerciseResource).build();
    }

    @Before
    public void initTest() {
        exerciseRepository.deleteAll();
        exercise = new Exercise();
        exercise.setIdentifier(DEFAULT_IDENTIFIER);
        exercise.setType(DEFAULT_TYPE);
        exercise.setName(DEFAULT_NAME);
        exercise.setCommentary(DEFAULT_COMMENTARY);
    }

    @Test
    public void createExercise() throws Exception {
        // Validate the database is empty
        assertThat(exerciseRepository.findAll()).hasSize(0);

        // Create the Exercise
        restExerciseMockMvc.perform(post("/app/rest/exercises")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(exercise)))
                .andExpect(status().isOk());

        // Validate the Exercise in the database
        List<Exercise> exercises = exerciseRepository.findAll();
        assertThat(exercises).hasSize(1);
        Exercise testExercise = exercises.iterator().next();
        assertThat(testExercise.getIdentifier()).isEqualTo(DEFAULT_IDENTIFIER);
        assertThat(testExercise.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testExercise.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testExercise.getCommentary()).isEqualTo(DEFAULT_COMMENTARY);
    }

    @Test
    public void getAllExercises() throws Exception {
        // Initialize the database
        exerciseRepository.save(exercise);

        // Get all the exercises
        restExerciseMockMvc.perform(get("/app/rest/exercises"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id").value(exercise.getId()))
                .andExpect(jsonPath("$.[0].identifier").value(DEFAULT_IDENTIFIER.toString()))
                .andExpect(jsonPath("$.[0].type").value(DEFAULT_TYPE.toString()))
                .andExpect(jsonPath("$.[0].name").value(DEFAULT_NAME.toString()))
                .andExpect(jsonPath("$.[0].commentary").value(DEFAULT_COMMENTARY.toString()));
    }

    @Test
    public void getExercise() throws Exception {
        // Initialize the database
        exerciseRepository.save(exercise);

        // Get the exercise
        restExerciseMockMvc.perform(get("/app/rest/exercises/{id}", exercise.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(exercise.getId()))
            .andExpect(jsonPath("$.identifier").value(DEFAULT_IDENTIFIER.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.commentary").value(DEFAULT_COMMENTARY.toString()));
    }

    @Test
    public void getNonExistingExercise() throws Exception {
        // Get the exercise
        restExerciseMockMvc.perform(get("/app/rest/exercises/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateExercise() throws Exception {
        // Initialize the database
        exerciseRepository.save(exercise);

        // Update the exercise
        exercise.setIdentifier(UPDATED_IDENTIFIER);
        exercise.setType(UPDATED_TYPE);
        exercise.setName(UPDATED_NAME);
        exercise.setCommentary(UPDATED_COMMENTARY);
        restExerciseMockMvc.perform(post("/app/rest/exercises")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(exercise)))
                .andExpect(status().isOk());

        // Validate the Exercise in the database
        List<Exercise> exercises = exerciseRepository.findAll();
        assertThat(exercises).hasSize(1);
        Exercise testExercise = exercises.iterator().next();
        assertThat(testExercise.getIdentifier()).isEqualTo(UPDATED_IDENTIFIER);
        assertThat(testExercise.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testExercise.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testExercise.getCommentary()).isEqualTo(UPDATED_COMMENTARY);;
    }

    @Test
    public void deleteExercise() throws Exception {
        // Initialize the database
        exerciseRepository.save(exercise);

        // Get the exercise
        restExerciseMockMvc.perform(delete("/app/rest/exercises/{id}", exercise.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Exercise> exercises = exerciseRepository.findAll();
        assertThat(exercises).hasSize(0);
    }
}
