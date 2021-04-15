package com.news.news.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

// Esto nos va a serbir para hacer llamados asyncronos

@Configuration //cuando hacemos el llamado async se hace en base a esta configuracion // se haran primero ya q otros llamados dependen d esta config
@EnableAsync // con esto le decimos q puede tener partes de codigo async - paralelismo -
public class AsyncConfiguration {

    @Bean(name = "taskExecutor") // dice q clase hace el manejo del paralelismo
    public Executor taskExecutor() {
        final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(2);
        executor.setQueueCapacity(10);
        executor.initialize();
        return executor;
    }
}