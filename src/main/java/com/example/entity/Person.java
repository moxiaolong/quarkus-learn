package com.example.entity;


import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = false)
@Entity
@Data
public class Person extends PanacheEntity {
    public String name;

}
