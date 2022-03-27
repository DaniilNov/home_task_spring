package com.example.home_task_spring.aspect;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class LoggingAspect {

    @Before(value = "allMethodInService()")
    public void beforeAllMethodInService(JoinPoint joinPoint) {
        log.info("beforeAllMethodInService:: START {}", joinPoint.getSignature().toShortString());
    }

    @After(value = "allMethodInService()")
    public void afterAllMethodInService(JoinPoint joinPoint) {
        log.info("afterAllMethodInService:: END {}", joinPoint.getSignature().toShortString());
    }

    @AfterThrowing(value = "allMethodInService()", throwing = "exception")
    public void afterAllMethodServiceThrow(JoinPoint.StaticPart staticPart, Exception exception) {
        log.info("afterAllMethodServiceThrow:: After {} with exception: {}", staticPart.toShortString(), exception.getMessage());
    }

    @Pointcut("within(com.example.home_task_spring.service.*)")
    public void allMethodInService() {
    }
}
