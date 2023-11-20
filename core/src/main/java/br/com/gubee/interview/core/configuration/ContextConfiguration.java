package br.com.gubee.interview.core.configuration;

import adapter.out.persistence.PowerStatsRepositoryJdbcImpl;
import application.port.out.CreatePowerStatsPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContextConfiguration {
    @Bean
    public CreatePowerStatsPort createPowerStatsPort () {
        return new PowerStatsRepositoryJdbcImpl();
    }
}
