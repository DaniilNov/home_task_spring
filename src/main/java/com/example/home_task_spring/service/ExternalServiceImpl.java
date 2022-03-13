package com.example.home_task_spring.service;

import com.example.home_task_spring.model.ExternalInfo;
import com.example.home_task_spring.model.annotation.CacheResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class ExternalServiceImpl implements ExternalService {

    HashMap<Integer, ExternalInfo> externalInfoHashMap = new HashMap<>();

    @PostConstruct
    public void init() {
        System.out.println("Наполняем HashMap");
        externalInfoHashMap.put(1, new ExternalInfo(1, null));
        externalInfoHashMap.put(2, new ExternalInfo(2, "hasInfo"));
        externalInfoHashMap.put(3, new ExternalInfo(3, "info"));
        externalInfoHashMap.put(4, new ExternalInfo(4, "information"));
    }

    @PreDestroy
    public void cleanMap() {
        externalInfoHashMap.clear();
        System.out.println("HashMap очищен");

    }

    @Override
    @CacheResult
    public ExternalInfo getExternalInfo(Integer id) {
        log.info("Вызов метода getExternalInfo c id: " + id);
        for (Map.Entry<Integer, ExternalInfo> integerExternalInfoEntry : externalInfoHashMap.entrySet()) {
            System.out.println("Key: " + integerExternalInfoEntry.getKey()
                    + "Value: " + integerExternalInfoEntry.getValue());
        }
        return externalInfoHashMap.get(id);
    }
}
