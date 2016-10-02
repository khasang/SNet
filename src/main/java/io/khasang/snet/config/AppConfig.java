package io.khasang.snet.config;

import io.khasang.snet.model.Hello;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public Hello hello() {
        return new Hello("Hello My First Bean");
    }
}
