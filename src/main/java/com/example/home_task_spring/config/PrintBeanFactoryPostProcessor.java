package com.example.home_task_spring.config;

import com.example.home_task_spring.model.annotation.CacheResult;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class PrintBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        for (String beanDefinitionName : beanFactory.getBeanDefinitionNames()) {
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanDefinitionName);

            if (beanDefinition.isPrototype()) {
                System.out.println("Есть Bean со Scope = Prototype");
                for (Method declaredMethod : beanDefinition.getClass().getDeclaredMethods()) {
                    //Не могу понять почему не работает
                    if (declaredMethod.isAnnotationPresent(CacheResult.class)) {
                        System.out.println("и аннотацией @CacheResult");
                    }
                }
            }
            System.out.println(beanDefinitionName);
        }
    }
}
