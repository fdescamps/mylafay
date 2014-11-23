package com.fdescamps.mylafay.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * A Repetition.
 */
@Document(collection = "T_REPETITION")
public class Repetition implements Serializable {

    @Id
    private String id;

    @Field("set_identifier")
    private Integer set_identifier;

    @Field("training_identifier")
    private Integer training_identifier;

    @Field("exercice_identifier")
    private String exercice_identifier;

    @Field("nb_of_repetitions")
    private Integer nb_of_repetitions;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getSet_identifier() {
        return set_identifier;
    }

    public void setSet_identifier(Integer set_identifier) {
        this.set_identifier = set_identifier;
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

    public Integer getNb_of_repetitions() {
        return nb_of_repetitions;
    }

    public void setNb_of_repetitions(Integer nb_of_repetitions) {
        this.nb_of_repetitions = nb_of_repetitions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Repetition repetition = (Repetition) o;

        if (id != null ? !id.equals(repetition.id) : repetition.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Repetition{" +
                "id=" + id +
                ", set_identifier='" + set_identifier + "'" +
                ", training_identifier='" + training_identifier + "'" +
                ", exercice_identifier='" + exercice_identifier + "'" +
                ", nb_of_repetitions='" + nb_of_repetitions + "'" +
                '}';
    }
}
