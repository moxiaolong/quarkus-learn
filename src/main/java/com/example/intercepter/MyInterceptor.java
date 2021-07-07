package com.example.intercepter;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@MyEvent
@Interceptor
public class MyInterceptor {

    @AroundInvoke
    public Object logEvent(InvocationContext invocationContext) throws Exception {
        return invocationContext.proceed();
    }
}
