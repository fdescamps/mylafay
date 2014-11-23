package com.fdescamps.mylafay.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * A Execution.
 */
@Document(collection = "T_EXECUTION")
public class Execution implements Serializable {

    @Id
    private String id;

    @Field("training_identifier")
    private Integer training_identifier;

    @Field("exercice_identifier")
    private String exercice_identifier;

    @Field("set")
    private Integer set;

    @Field("commentary")
    private String commentary;

    @Field("difficulty")
    private String difficulty;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getTraining_identifier() {
        return training_identifier;
    }

    public void setTraining_identifier(Integer training_identifier) {
        this.training_identifier = training_identifier;
    }

    public String getExercice_identifier() {
        return exercice_identifier;
    }

    public void setExercice_identifier(String exercice_identifier) {
        this.exercice_identifier = exercice_identifier;
    }

    public Integer getSet() {
        return set;
    }

    public void setSet(Integer set) {
        this.set = set;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Execution execution = (Execution) o;

        if (id != null ? !id.equals(execution.id) : execution.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Execution{" +
                "id=" + id +
                ", training_identifier='" + training_identifier + "'" +
                ", exercice_identifier='" + exercice_identifier + "'" +
                ", set='" + set + "'" +
                ", commentary='" + commentary + "'" +
                ", difficulty='" + difficulty + "'" +
                '}';
    }
}
