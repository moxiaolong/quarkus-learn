package com.example.controller;

import com.example.entity.Person;
import com.example.exceptionmapper.BusinessException;
import com.example.exceptionmapper.ErrorEnum;
import com.example.service.ExampleService;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/hello")
public class ExampleResource {
    @Inject
    ExampleService exampleService;

    /**
     * 项目是通过quarkus:Dev启动的，无需启动类
     * 支持热修改，实时生效
     */

    /**
     * 默认不支持JSON格式 需要引入resteasy-jackson包(或jsonb)，而后不需要Produces注解默认将变为JSON格式
     * //@Produces(MediaType.APPLICATION_JSON)
     */
    @GET
    public Map<String, String> hello() {
        //如果是响应式的easyRest,执行阻塞代码时，需要用@Blocking标记，否则将阻塞vertX的eventLoop线程。
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new HashMap<>(1) {{
            put("hello", "World");
        }};
    }

    /**
     * 后边的探讨都会基于响应式编程，响应式是未来。
     * Quarkus 默认使用 Mutiny 响应式包
     * <p>
     * 引用：
     * Quarkus与Java Streams、Eclipse MicroProfile Reactive规范和
     * SmallRye Mutiny进行了集成，这些集成使支持异步的HTTP接口变得很
     * 容易。首先，需要做的是确定想使用哪些库。如果想使用原生的
     * Streams或者MicroProfile Reactive规范，需要添加quarkus-smallrye-reactive-streams-operators扩展。
     * 如果想使用SmallRye Mutiny，需要添加
     * quarkus-resteasy-mutiny扩展到你的项目中。
     */


    @GET
    @Path("allPerson")
    public Multi<Person> allPerson() {
        return exampleService.getAllPerson();
    }


    @GET
    @Path("allPersonPublic")
    public Multi<Person> allPersonPublic() {
        return exampleService.getAllPerson();
    }

    @GET
    @Path("add")
    public Uni<Person> add() {
        return exampleService.addPerson();
    }

    @GET
    @Path("testBlock")
    public Uni<List> testBlock() {
        return exampleService.testBlock();
    }

    @GET
    @Path("testBlock2")
    public Uni<Person> testBlock2() {
        return exampleService.testBlock2();
    }

    @GET
    @Path("testBlock3")
    public Uni<Person> testBlock3() {
        return exampleService.testBlock3();
    }

    @GET
    @Path("testBlock4")
    public Uni<Person> testBlock4() {
        return exampleService.testBlock4();
    }

    @POST
    @Path("testValidator")
    public Uni<Person> testValidator(@Valid Person person) {
        return Uni.createFrom().item(person);
    }

    @GET
    @Path("testResponse")
    public Uni<Response> testValidator() {
        // return Uni.createFrom().item().build());
        return null;
    }


    @GET
    @Path("testException")
    public Uni testException() {
        throw new RuntimeException("runtime");
    }

    @GET
    @Path("testBusinessException")
    public Uni testBusinessException() {
       //return Uni.createFrom().item(Response.serverError().entity(ErrorEnum.UNKNOWN).build());
        throw new RuntimeException("1111");
    }

}