package com.example.service;

import com.example.entity.Person;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.core.Vertx;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class ExampleService {

    @Inject
    Vertx vertx;


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

        return Person.find("name", "测试").stream();
    }

    /**
     * 测试了阻塞的SQL语句，不会阻塞eventLoop线程
     */
    public Uni<List> testBlock() {
        return Person.getSession().createNativeQuery(
                "SELECT NOW() as now,SLEEP(10),NOW() as away", List.class).getSingleResult();
    }

    /**
     * 阻塞eventLoop线程
     */
    public Uni<Person> testBlock2() {
        return Uni.createFrom().item(() -> {
                    try {
                        Thread.sleep(5000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return new Person();
                }
        );
    }


    /**
     * 不会阻塞eventLoop线程
     */
    public Uni<Person> testBlock3() {
        return vertx.executeBlocking(Uni.createFrom().item(() -> {
                    try {
                        Thread.sleep(5000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return new Person();
                }
        ));
    }


}
