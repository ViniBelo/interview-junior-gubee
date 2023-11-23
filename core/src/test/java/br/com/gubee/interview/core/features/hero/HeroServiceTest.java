package br.com.gubee.interview.core.features.hero;


import br.com.gubee.interview.core.hero.hero.HeroRepository;
import br.com.gubee.interview.core.hero.hero.HeroService;
import br.com.gubee.interview.core.hero.domain.model.Hero;
import br.com.gubee.interview.model.enums.Race;
import br.com.gubee.interview.model.request.CreateHeroRequest;
import br.com.gubee.interview.model.response.CreateHeroResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.UUID;

public class HeroServiceTest {
    HeroRepository heroRepository = new HeroRepositoryStub(null);

    @InjectMocks
    private HeroService heroService = new HeroService(heroRepository);

    @Test
    public void createHeroWithAllRequiredArguments() {
        //given
        UUID heroId;
        UUID powerStatsId = UUID.fromString("906ac564-7d1a-4691-8df2-abf20e7ce9ee");
        Hero hero = new Hero(createHeroRequest(), powerStatsId);

        //when
        heroId = heroService.create(createHeroRequest(), powerStatsId);

        //then
        Assertions.assertNotNull(heroId);
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

    @Test
    public void getHeroWithAnExistingId() {
        //given
        UUID heroId;
        UUID powerStatsId = UUID.fromString("906ac564-7d1a-4691-8df2-abf20e7ce9ee");
        Hero hero = new Hero(createHeroRequest(), powerStatsId);
        heroId = heroService.create(createHeroRequest(), powerStatsId);

        //when
        CreateHeroResponse foundHero = heroService.findById(heroId);

        //then
        Assertions.assertEquals(foundHero, createHeroResponse());
    }

    @Test
    public void getHeroWithAnExistingName() {
        //given
        UUID powerStatsId = UUID.fromString("906ac564-7d1a-4691-8df2-abf20e7ce9ee");
        Hero hero = new Hero(createHeroRequest(), powerStatsId);
        heroService.create(createHeroRequest(), powerStatsId);

        //when
        heroService.findByName("Batman");

        //then
        Assertions.assertEquals(heroService.findByName("Batman"), createHeroResponse());
    }

    @Test
    public void getHeroWithAnInexistingName() {
        //given
        UUID powerStatsId = UUID.fromString("906ac564-7d1a-4691-8df2-abf20e7ce9ee");
        Hero hero = new Hero(createHeroRequest(), powerStatsId);
        heroService.create(createHeroRequest(), powerStatsId);

        //when
        heroService.findByName("Green Lantern");

        //then
        Assertions.assertEquals(heroService.findByName("Green Lantern"), null);
    }

    private CreateHeroResponse createHeroResponse() {
        return CreateHeroResponse.builder()
                .name("Batman")
                .agility(5)
                .dexterity(8)
                .strength(6)
                .intelligence(10)
                .race(Race.HUMAN)
                .build();
    }
}
