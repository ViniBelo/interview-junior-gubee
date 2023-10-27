package br.com.gubee.interview.core.features.hero;


import br.com.gubee.interview.model.Hero;
import br.com.gubee.interview.model.enums.Race;
import br.com.gubee.interview.model.request.CreateHeroRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.UUID;

import static org.mockito.Mockito.*;

public class HeroServiceTest {
    HeroRepository heroRepository = mock(HeroRepositoryStub.class);

    @InjectMocks
    private HeroService heroService = new HeroService(heroRepository);

    @Test
    public void createHeroWithAllRequiredArguments() {
        //given
        UUID powerStatsId = UUID.fromString("906ac564-7d1a-4691-8df2-abf20e7ce9ee");
        Hero hero = new Hero(createHeroRequest(), powerStatsId);

        //when
        heroService.create(createHeroRequest(), powerStatsId);

        //then
        verify(heroRepository, times(1)).create(hero);
        Assertions.assertEquals(heroService.create(createHeroRequest(), powerStatsId), hero.getId());
    }

    private CreateHeroRequest createHeroRequest() {
        return CreateHeroRequest.builder()
                .name("Batman")
                .agility(5)
                .dexterity(8)
                .strength(6)
                .intelligence(10)
                .race(Race.HUMAN)
                .build();
    }
}
