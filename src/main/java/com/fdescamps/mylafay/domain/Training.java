package com.fdescamps.mylafay.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.joda.deser.LocalDateDeserializer;
import com.fdescamps.mylafay.domain.util.CustomLocalDateSerializer;
import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * A Training.
 */
@Document(collection = "T_TRAINING")
public class Training implements Serializable {

    @Id
    private String id;

    @Field("identifier")
    private Integer identifier;

    @Field("training_number")
    private Integer training_number;

    @Field("user")
    private String user;

    @Field("type")
    private String type;

    @Field("commentary")
    private String commentary;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = CustomLocalDateSerializer.class)
    @Field("date")
    private LocalDate date;

    @Field("duration")
    private Integer duration;

    @Field("warming")
    private String warming;

    @Field("stretching")
    private String stretching;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Integer identifier) {
        this.identifier = identifier;
    }

    public Integer getTraining_number() {
        return training_number;
    }

    public void setTraining_number(Integer training_number) {
        this.training_number = training_number;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getWarming() {
        return warming;
    }

    public void setWarming(String warming) {
        this.warming = warming;
    }

    public String getStretching() {
        return stretching;
    }

    public void setStretching(String stretching) {
        this.stretching = stretching;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Training training = (Training) o;

        if (id != null ? !id.equals(training.id) : training.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Training{" +
                "id=" + id +
                ", identifier='" + identifier + "'" +
                ", training_number='" + training_number + "'" +
                ", user='" + user + "'" +
                ", type='" + type + "'" +
                ", commentary='" + commentary + "'" +
                ", date='" + date + "'" +
                ", duration='" + duration + "'" +
                ", warming='" + warming + "'" +
                ", stretching='" + stretching + "'" +
                '}';
    }
}
