package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.core.features.connectors.HeroPowerStatsConnector;
import br.com.gubee.interview.model.Hero;
import br.com.gubee.interview.model.enums.Race;
import br.com.gubee.interview.model.request.CreateHeroRequest;
import br.com.gubee.interview.model.response.CreateHeroResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("it")
public class HeroServiceIT {

    @Autowired
    private HeroService heroService;

    @Autowired
    private HeroPowerStatsConnector heroPowerStatsConnector;

    @Autowired
    private HeroRepository heroRepository;

    @Test
    public void createHeroWithAllRequiredArguments() {
        //given
        Hero hero = new Hero(createHeroRequest(), UUID.randomUUID());
        //when
        heroService.create(createHeroRequest(), UUID.randomUUID());

        //then
        verify(heroRepository, times(1)).create(hero);
        Assertions.assertEquals(heroService.create(createHeroRequest(), UUID.randomUUID()), UUID.randomUUID());
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
    public void findHeroById() {
        //given
        //when
        heroService.findById(UUID.randomUUID());

        //then
        verify(heroRepository, times(1)).findById(UUID.randomUUID());
        Assertions.assertEquals(heroService.findById(UUID.randomUUID()), createHeroResponse());
    }

    @Test
    public void findByName() {
        //given
        //when
        heroService.findById(UUID.randomUUID());

        //then
        verify(heroRepository, times(1)).findById(UUID.randomUUID());
        Assertions.assertEquals(heroService.findById(UUID.randomUUID()), createHeroResponse());
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
