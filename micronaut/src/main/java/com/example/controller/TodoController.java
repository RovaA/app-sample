package com.example.controller;

import com.example.service.GreetingService;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import jakarta.inject.Inject;

@Controller("/todo")
public class TodoController {

    @Inject
    private GreetingService greetingService;

    @Get("/greet")
    public String greet() {
        greetingService.hello();
        return "{ message: 'Just to greet' }";
    }
}
