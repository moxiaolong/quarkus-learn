package com.example.service;

import com.example.entity.Person;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ExampleService {


    /**
     * 数据库操作使用了PANACHE,
     * PANACHE是一个简化HIBERNATE的包，基于HIBERNATE
     * Quarkus提供了对应响应式的包，包括数据库驱动都要使用响应式才能保证不阻塞
     */
    public Uni<Person> addPerson() {
        Person person = new Person();
        person.setName("测试");
        return person.persistAndFlush();
    }

    public Multi<Person> getAllPerson() {
        return Person.findAll().stream();
    }
}
