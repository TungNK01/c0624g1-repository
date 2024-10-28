package com.codegym.c0624g1repository.logger;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Logger {
    @AfterThrowing(pointcut = "execution(public * com.codegym.c0624g1repository.service.ICustomerService.*(..))")
    public void log() {
        System.out.println("[CMS] ERROR!");
    }
}
