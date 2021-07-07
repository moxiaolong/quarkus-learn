package com.example.filter;

import io.quarkus.vertx.http.runtime.filters.Filters;
import io.vertx.core.Vertx;
import io.vertx.ext.web.handler.BodyHandler;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Priority;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.ws.rs.ext.Provider;

@Provider
@Priority(9999)
@Slf4j
//implements ContainerRequestFilter {
public class LogFilter {

    /**
     * 如果使用@Provider需要包装
     * 包装原因见http://www.kailing.pub/article/index/arcid/305.html
     */
    @Inject
    javax.inject.Provider<Vertx> vertx;


    public void initfilter(@Observes Filters filters) {

        filters.register(routingContext -> {
            vertx.get().executeBlocking(
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
                                routingContext.next();
                        future.complete();
                    }
            );
        }, 100);
    }


        //    @Override
        //    public void filter(ContainerRequestContext requestContext) throws IOException {
            //阻塞eventLoop
        //    }
}