package ru.otus.springrefactor.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    @Primary
    public DataSource dataSource() {
        return DataSourceBuilder
            .create()
            .username("sa")
            .password("")
            .url("jdbc:h2:mem:test")
            .driverClassName("org.h2.Driver")
            .build();
    }
}
