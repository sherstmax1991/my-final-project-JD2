package by.itacademy.config;

import by.itacademy.aspect.LoggableAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"by.itacademy.service"})
public class AspectConfig {

    @Bean
    public LoggableAspect audienceAspect() {
        return new LoggableAspect();
    }
}
