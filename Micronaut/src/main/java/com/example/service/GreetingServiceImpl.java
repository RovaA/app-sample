package com.example.service;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class GreetingServiceImpl implements GreetingService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void hello() {
        logger.info("This is an Hello world");
    }
}
