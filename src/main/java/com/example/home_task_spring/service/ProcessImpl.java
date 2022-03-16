package com.example.home_task_spring.service;

import com.example.home_task_spring.model.ExternalInfo;
import org.springframework.stereotype.Component;

@Component
public class ProcessImpl implements Process{
    @Override
    public boolean run(ExternalInfo externalInfo) {
        return false;
    }
}
