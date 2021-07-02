package com.example;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.HashMap;
import java.util.Map;

@Path("/hello")
public class ExampleResource {

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
        return new HashMap<>(1) {{
            put("hello", "World");
        }};
    }
}