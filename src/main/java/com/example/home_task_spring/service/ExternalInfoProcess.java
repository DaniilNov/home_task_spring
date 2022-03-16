package com.example.home_task_spring.service;

import com.example.home_task_spring.model.ExternalInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

@Slf4j
public class ExternalInfoProcess implements Process {

    @Value("${id-not-process}")
    private Integer id;

    public boolean run(ExternalInfo externalInfo) {
        if (externalInfo.getId().equals(id)) {
            log.info("false");
            return false;
        }
        log.info("true");
        return true;
    }
}