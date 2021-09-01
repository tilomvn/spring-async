package com.springasync;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;


@Configuration
@EnableAsync
public class SpringAsyncConfig implements AsyncConfigurer {

    Logger log = LoggerFactory.getLogger(this.getClass());
    
    @Override
    public Executor getAsyncExecutor() {
        return new ThreadPoolTaskExecutor();
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new AsyncUncaughtExceptionHandler() {
            @Override
            public void handleUncaughtException(Throwable throwable, Method method, Object... obj) {
                log.info("Exception message - " + throwable.getMessage());
                log.info("Method name - " + method.getName());
                for (Object param : obj) {
                    log.info("Parameter value - " + param);
                }
            }
        };
    }

    @Bean
    Executor threadPoolTaskExecutor() {
        return getAsyncExecutor();
    }
}