package Configuration;

import adapter.HeroAdapter;
import adapter.PowerStatsAdapter;
import controller.FetchHeroController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(WebAdapterConfiguration.class)
public class ControllerConfiguration {
    HeroAdapter heroAdapter;
    PowerStatsAdapter powerStatsAdapter;
    @Bean
    public FetchHeroController fetchHeroController() {
        return new FetchHeroController(heroAdapter, powerStatsAdapter);
    }

    @Bean
    public FetchHeroController fetchHeroController(
            HeroAdapter heroAdapter,
            PowerStatsAdapter powerStatsAdapter
    ) {
        return new FetchHeroController(heroAdapter, powerStatsAdapter);
    }
}
