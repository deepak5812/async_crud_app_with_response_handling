    package com.example.demo;

    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.scheduling.annotation.EnableAsync;
    import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

    import java.util.concurrent.Executor;

    @Configuration
    @EnableAsync
    public class AsyncConfiguration {

        @Bean(name = "asyncExecutor")
        public Executor asyncExecutor()  {

            ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
            executor.setCorePoolSize(10);
            executor.setMaxPoolSize(100);
            executor.setQueueCapacity(10);
            executor.setThreadNamePrefix("AsynchThread-");
            executor.initialize();
            return executor;
        }
    }