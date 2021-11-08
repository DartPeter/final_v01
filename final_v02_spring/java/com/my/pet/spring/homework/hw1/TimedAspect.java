package com.my.pet.spring.homework.hw1;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimedAspect {

    @Around("@annotation(com.my.pet.spring.homework.hw1.Timed)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        final long start = System.currentTimeMillis();
        final Object proceed = joinPoint.proceed();
        final long executionTime = System.currentTimeMillis() - start;
        LoggerFactory.getLogger(TimedAspect.class).atInfo().log(joinPoint.getSignature() + " executed in " + executionTime + "ms");
        return proceed;
    }

}