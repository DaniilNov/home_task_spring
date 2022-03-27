package com.example.home_task_spring.service;

import com.example.home_task_spring.model.ExternalInfo;
import com.example.home_task_spring.model.annotation.CacheResult;
import com.example.home_task_spring.model.annotation.CheckRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class ExternalServiceImpl implements ExternalService {

    HashMap<Integer, ExternalInfo> externalInfoHashMap = new HashMap<>();

    @Value("${id-not-process}")
    private Integer id;

    @PostConstruct
    public void init() {
        System.out.println("Наполняем HashMap");
        externalInfoHashMap.put(1, new ExternalInfo(1, "hasInfo"));
        externalInfoHashMap.put(2, new ExternalInfo(2, null));
        externalInfoHashMap.put(3, new ExternalInfo(3, "info"));
        externalInfoHashMap.put(4, new ExternalInfo(4, "information"));
    }

    @PreDestroy
    public void cleanMap() {
        externalInfoHashMap.clear();
        System.out.println("HashMap очищен");

    }

    @CheckRequest
    @CacheResult
    @Override
    public ExternalInfo getExternalInfo(Integer id) {
        ExternalInfo externalInfo = externalInfoHashMap.get(id);
        if (externalInfo == null) {
            throw new RuntimeException("ExternalInfo with id: " + id + " не найден!");
        }
        return externalInfoHashMap.get(id);
    }
}
