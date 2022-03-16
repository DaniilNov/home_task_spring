package com.example.home_task_spring.config;

import com.example.home_task_spring.model.annotation.CacheResult;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Slf4j
public class PrintBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @SneakyThrows
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        for (String beanDefinitionName : beanFactory.getBeanDefinitionNames()) {
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanDefinitionName);

            if (beanDefinition.isPrototype()) {
                for (Method declaredMethod : Class.forName(beanDefinition.getBeanClassName()).getDeclaredMethods()) {
                    if (declaredMethod.isAnnotationPresent(CacheResult.class)) {
                        log.warn("Bean: {} with Scope = prototype mark annotation @CacheResult", beanDefinitionName);
                    }
                }
            }
        }
    }
}
