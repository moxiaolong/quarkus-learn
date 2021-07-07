package com.example.filter;

import com.example.entity.Person;
import io.quarkus.arc.profile.IfBuildProfile;
import io.quarkus.vertx.http.runtime.filters.Filters;
import io.vertx.core.Vertx;
import io.vertx.ext.web.handler.BodyHandler;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Priority;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.ws.rs.ext.Provider;
//这个注解可以根据环境决定加不加在Bean 感觉可以做很多事，比如在测试环境启动过滤器注入超级用户，或者注入不同颗粒度的日志过滤器
//@IfBuildProfile
@Provider
@Priority(9999)
@Slf4j
//implements ContainerRequestFilter {
public class LogFilter {

//    /**
//     * 如果使用@Provider需要包装
//     * 包装原因见http://www.kailing.pub/article/index/arcid/305.html
//     */
//    @Inject
//    javax.inject.Provider<Vertx> vertx;


    //Observes 事件观察是一种非常强大的、用最小的开销来解耦逻辑的方式。
    public void initfilter(@Observes Filters filters) {
        filters.register(routingContext ->
                routingContext.vertx().executeBlocking(
                (future) -> {
                    //这种写法可以执行阻塞代码  或许可以在这里验证用户权限
                    //   try {
                    //       Thread.sleep(5000L);
                    //   } catch (InterruptedException e) {
                    //       e.printStackTrace();
                    //   }

                    log.info("收到请求url:{} 参数:{} 请求体:{}",
                            routingContext.normalizedPath(),
                            routingContext.queryParams(),
                            //这样拿不到请求体
                            routingContext.getBodyAsString());
                    if ("/hello/testContext".equals(routingContext.normalizedPath())) {
                        //测试routingContext的生命周期
                        routingContext.put("user", new Person());
                    }
                    routingContext.next();
                    future.complete();
                }
        ), 100);
    }


    //    @Override
    //    public void filter(ContainerRequestContext requestContext) throws IOException {
    //阻塞eventLoop
    //    }
}