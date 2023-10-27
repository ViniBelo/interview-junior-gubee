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
        CreateHeroResponse heroResponse = createHeroResponse();

        //when
        heroService.findById(UUID.randomUUID());

        //then
        verify(heroRepository, times(1)).findById(UUID.randomUUID());
        Assertions.assertEquals(heroService.findById(UUID.randomUUID()), heroResponse);
    }

    @Test
    public void findByName() {
        //given
        CreateHeroResponse heroResponse = createHeroResponse();

        //when
        heroService.findByName(heroResponse.getName());

        //then
        verify(heroRepository, times(1)).findByName(heroResponse.getName());
        Assertions.assertEquals(heroService.findByName(createHeroRequest().getName()), heroResponse);
    }

    @Test
    public void deleteHero() {
        //given
        CreateHeroResponse heroResponse = createHeroResponse();

        //when
        heroService.deleteHero(UUID.randomUUID());

        //then
        verify(heroRepository, times(1)).delete(UUID.randomUUID());
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
