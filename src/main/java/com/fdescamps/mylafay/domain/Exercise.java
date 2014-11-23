package com.fdescamps.mylafay.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * A Exercise.
 */
@Document(collection = "T_EXERCISE")
public class Exercise implements Serializable {

    @Id
    private String id;

    @Field("identifier")
    private String identifier;

    @Field("type")
    private String type;

    @Field("name")
    private String name;

    @Field("commentary")
    private String commentary;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Exercise exercise = (Exercise) o;

        if (id != null ? !id.equals(exercise.id) : exercise.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "id=" + id +
                ", identifier='" + identifier + "'" +
                ", type='" + type + "'" +
                ", name='" + name + "'" +
                ", commentary='" + commentary + "'" +
                '}';
    }
}
