package com.example.home_task_spring.aspect;

import com.example.home_task_spring.model.ExternalInfo;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class CheckRequestAspect {

    @Value("${id-not-process}")
    private Integer id;

    @Around("allMethodAnnotationCheckResultAndHaveExternalInfo(externalInfo)")
    public Object findAllMethodAnnotationCheckResultAndHaveExternalInfo(ProceedingJoinPoint proceedingJoinPoint,
                                                                        ExternalInfo externalInfo) throws Throwable {
        log.info("Request: {} with {}", proceedingJoinPoint.getSignature().toShortString(), externalInfo);
        if (!id.equals(externalInfo.getId())) {
            return proceedingJoinPoint.proceed();
        } else {
            throw new RuntimeException("Decline!");
        }
    }

    @Pointcut("@annotation(com.example.home_task_spring.model.annotation.CheckRequest) && args(externalInfo, ..)")
    public void allMethodAnnotationCheckResultAndHaveExternalInfo(ExternalInfo externalInfo) {
    }
}
