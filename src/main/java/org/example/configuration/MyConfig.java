package org.example.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan("org.example")
public class MyConfig {

    @Bean     // для совершение http запросов из rest клиента используем RestTemplate - вспомогательный класс Spring-а
    public RestTemplate restTemplate() { // этот бин используем для осуществление http реквестов
        return new RestTemplate();    // необходимо прописать зависимость в классе Communication


    }

}
