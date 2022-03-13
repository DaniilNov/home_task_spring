package com.example.home_task_spring.service;

import com.example.home_task_spring.model.ExternalInfo;
import com.example.home_task_spring.model.annotation.CacheResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Flow {

    ExternalService externalService;
    Process process;

    @Autowired
    public Flow(ExternalService externalService,
                @Lazy Process process) {
        this.externalService = externalService;
        this.process = process;
    }

    @CacheResult
    public void run(Integer id) {
        ExternalInfo externalInfo = externalService.getExternalInfo(id);

        if (externalInfo.getInfo() != null) {
            process.run(externalInfo);
        } else {
            System.out.println(process.getClass());
        }

    }
}
