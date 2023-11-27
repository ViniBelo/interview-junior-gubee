package Configuration;

import adapter.HeroAdapter;
import adapter.PowerStatsAdapter;
import application.port.in.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({PersistencePowerStatsAdapterConfiguration.class, PersistenceHeroAdapterConfiguration.class})
public class WebAdapterConfiguration {

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

    @Bean
    public HeroAdapter heroAdapter (
            FetchHeroUseCase fetchHeroUseCase,
            UpdateHeroUseCase updateHeroUseCase,
            RemoveHeroUseCase removeHeroUseCase,
            RegisterHeroUseCase registerHeroUseCase,
            BuildHeroDto buildHeroDto,
            PowerStatsAdapter powerStatsAdapter
    ) {
        return new HeroAdapter(
                fetchHeroUseCase,
                updateHeroUseCase,
                removeHeroUseCase,
                registerHeroUseCase,
                buildHeroDto,
                powerStatsAdapter
                );
    }
}
