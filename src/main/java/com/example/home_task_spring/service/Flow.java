package com.example.home_task_spring.service;

import com.example.home_task_spring.model.ExternalInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Flow {

    private final ExternalService externalService;
    private final Process process;

    @Autowired
    public Flow(ExternalService externalService,
                Process process) {
        this.externalService = externalService;
        this.process = process;
    }


    public void run(Integer id) {
        ExternalInfo externalInfo = externalService.getExternalInfo(id);
        if (externalInfo.getInfo() != null) {
            process.run(externalInfo);
        } else {
            log.info("Not run process: {}", externalInfo);
        }
    }
}
