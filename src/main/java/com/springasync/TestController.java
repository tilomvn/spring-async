package com.springasync;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/test")
    @Async("threadPoolTaskExecutor")
    public CompletableFuture<ResponseModel> testAsyncWithResponse() {
        return CompletableFuture.completedFuture(new ResponseModel("body"));
    }

    @GetMapping("/test1")
    @Async("threadPoolTaskExecutor")
    public Future<String> testAsyncNonResponse() {
        return new AsyncResult<String>("value");
    }

    class ResponseModel {
        String value;

        ResponseModel(String value) {
            this.value = value;
        }

        public String getValue() { return value; }
    }
}