package com.example.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import io.smallrye.mutiny.Multi;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@EqualsAndHashCode(callSuper = false)
@Entity
@Data
public class Person extends PanacheEntity {

    @NotBlank
    public String name;
    @JsonIgnore
    public String password;

    public static Multi<Person> findByName(String name) {
        return Person.find("name", name).stream();
    }

}
