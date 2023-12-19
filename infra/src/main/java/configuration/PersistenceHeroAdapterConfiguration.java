package configuration;

import application.port.in.*;
import application.port.out.*;
import application.service.*;
import hero.HeroPersistenceAdapter;
import hero.HeroRepositoryJdbcImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
@Import(JdbcConfiguration.class)
public class PersistenceHeroAdapterConfiguration {
    @Bean
    public BuildHeroDto buildHeroDto() {
        return new BuildHeroDtoService();
    };

    @Bean
    public CreateHeroPort createHeroPort (NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        return new HeroPersistenceAdapter(new HeroRepositoryJdbcImpl(namedParameterJdbcTemplate), buildHeroDto());
    }

    @Bean
    public DeleteHeroPort deleteHeroPort (NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        return new HeroPersistenceAdapter(new HeroRepositoryJdbcImpl(namedParameterJdbcTemplate), buildHeroDto());
    }

    @Bean
    public FindHeroPort findHeroPort (NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        return new HeroPersistenceAdapter(new HeroRepositoryJdbcImpl(namedParameterJdbcTemplate), buildHeroDto());
    }

    @Bean
    public UpdateHeroPort updateHeroPort (NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        return new HeroPersistenceAdapter(new HeroRepositoryJdbcImpl(namedParameterJdbcTemplate), buildHeroDto());
    }

    @Bean
    public FindStatsFromComparedHeroes findStatsFromComparedHeroes (NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        return new HeroPersistenceAdapter(new HeroRepositoryJdbcImpl(namedParameterJdbcTemplate), buildHeroDto());
    }

    @Bean
    public RegisterHeroUseCase registerHeroUseCase (NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        return new RegisterHeroService(createHeroPort(namedParameterJdbcTemplate));
    }

    @Bean
    public RemoveHeroUseCase removeHeroUseCase (NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        return new RemoveHeroService(deleteHeroPort(namedParameterJdbcTemplate));
    }

    @Bean
    public FetchHeroUseCase fetchHeroUseCase (NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        return new FetchHeroService(findHeroPort(namedParameterJdbcTemplate));
    }

    @Bean
    public UpdateHeroUseCase updateHeroUseCase (NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        return new UpdateHeroService(updateHeroPort(namedParameterJdbcTemplate));
    }
}
