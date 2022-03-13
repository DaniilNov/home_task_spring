package com.example.home_task_spring.service;

import com.example.home_task_spring.model.ExternalInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Lazy
public class ExternalInfoProcess implements Process {

    @Value("${id-not-process}")
    private Integer id;

    public boolean run(ExternalInfo externalInfo) {

        if (externalInfo.getId().equals(id)) {
            System.out.println("false");
            return false;
        }

        System.out.println("true");
        return true;
    }
}