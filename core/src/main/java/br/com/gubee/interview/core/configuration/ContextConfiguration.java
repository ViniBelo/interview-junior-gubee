package br.com.gubee.interview.core.configuration;

import adapter.in.PowerStatsAdapter;
import adapter.out.persistence.PowerStatsPersistenceAdapter;
import adapter.out.persistence.PowerStatsRepositoryJdbcImpl;
import application.port.in.CreatePowerStatsUseCase;
import application.port.in.DeletePowerStatsUseCase;
import application.port.in.UpdatePowerStatsUseCase;
import application.port.out.CreatePowerStatsPort;
import application.port.out.DeletePowerStatsPort;
import application.port.out.UpdatePowerStatsPort;
import application.service.CreatePowerStatsService;
import application.service.DeletePowerStatsService;
import application.service.UpdatePowerStatsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class ContextConfiguration {
    @Bean
    public CreatePowerStatsPort createPowerStatsPort (NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        return new PowerStatsPersistenceAdapter(new PowerStatsRepositoryJdbcImpl(namedParameterJdbcTemplate));
    }

    @Bean
    public UpdatePowerStatsPort updatePowerStatsPort (NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        return new PowerStatsPersistenceAdapter(new PowerStatsRepositoryJdbcImpl(namedParameterJdbcTemplate));
    }

    @Bean
    public DeletePowerStatsPort deletePowerStatsPort (NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        return new PowerStatsPersistenceAdapter(new PowerStatsRepositoryJdbcImpl(namedParameterJdbcTemplate));
    }

    @Bean
    public CreatePowerStatsUseCase createPowerStatsUseCase (NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        return new CreatePowerStatsService(createPowerStatsPort(namedParameterJdbcTemplate));
    }

    @Bean
    public UpdatePowerStatsUseCase updatePowerStatsUseCase (NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        return new UpdatePowerStatsService(updatePowerStatsPort(namedParameterJdbcTemplate));
    }

    @Bean
    public DeletePowerStatsUseCase deletePowerStatsUseCase (NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        return new DeletePowerStatsService(deletePowerStatsPort(namedParameterJdbcTemplate));
    }

    @Bean
    public PowerStatsAdapter powerStatsAdapter (
            CreatePowerStatsUseCase createPowerStatsUseCase,
            UpdatePowerStatsUseCase updatePowerStatsUseCase,
            DeletePowerStatsUseCase deletePowerStatsUseCase
            ) {
        return new PowerStatsAdapter(
                createPowerStatsUseCase,
                updatePowerStatsUseCase,
                deletePowerStatsUseCase
        );
    }
}
