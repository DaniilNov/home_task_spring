package com.example.home_task_spring;

import com.example.home_task_spring.model.ExternalInfo;
import com.example.home_task_spring.service.ExternalService;
import com.example.home_task_spring.service.Flow;
import com.example.home_task_spring.service.Process;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;

@EnableAspectJAutoProxy
@ComponentScan
@PropertySource("classpath:application.properties")
public class HomeTaskSpringApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(HomeTaskSpringApplication.class);
        ExternalService externalService = applicationContext.getBean(ExternalService.class);
        Process process = applicationContext.getBean(Process.class);
        ExternalInfo externalInfo = externalService.getExternalInfo(1);
        process.run(externalInfo);
        System.out.println(externalInfo);

        Flow flow = applicationContext.getBean(Flow.class);
        flow.run(1);
        flow.run(2);
        flow.run(2);
        flow.run(3);
        flow.run(4);
//            flow.run(5);
        applicationContext.close();
    }
}

