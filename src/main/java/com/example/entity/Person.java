package com.example.entity;


import com.example.view.Views;
import com.fasterxml.jackson.annotation.JsonView;
import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import io.smallrye.mutiny.Multi;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = false)
@Entity
@Data
public class Person extends PanacheEntity {


    public String name;

    /**
     * 仅在视图是私有的端点返回
     */
    @JsonView(Views.Private.class)
    public String password;
    @JsonView(Views.Private.class)
    public String getPassword() {
        return password;
    }

    public static Multi<Person> findByName(String name) {
        return Person.find("name", name).stream();
    }

}
