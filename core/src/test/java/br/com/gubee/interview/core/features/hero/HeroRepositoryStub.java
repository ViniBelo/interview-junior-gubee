package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.core.hero.hero.HeroRepository;
import br.com.gubee.interview.core.hero.domain.model.Hero;
import br.com.gubee.interview.model.PowerStats;
import br.com.gubee.interview.model.request.UpdateHeroRequest;
import br.com.gubee.interview.model.response.CreateHeroResponse;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class HeroRepositoryStub extends HeroRepository {

    Map<UUID, Hero> heroesMappedById = new HashMap<>();
    Map<String, Hero> heroesMappedByName = new HashMap<>();
    Map<UUID, PowerStats> powerStats = new HashMap<>();

    public HeroRepositoryStub(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(namedParameterJdbcTemplate);
    }

    public UUID create(Hero hero) {
        try {
            UUID heroId = UUID.randomUUID();
            heroesMappedById.put(heroId, hero);
            heroesMappedByName.put(hero.getName(), hero);
            powerStats.put(UUID.randomUUID(), new PowerStats(
                    null,
                    6,
                    5,
                    8,
                    10,
                    null,
                    null
            ));
            return heroId;
        } catch (NullPointerException e) {
            return null;
        }
    }

    public CreateHeroResponse findById(UUID id) {
        try {
            Hero hero = heroesMappedById.get(id);
            return CreateHeroResponse
                    .builder()
                    .name(hero.getName())
                    .agility(5)
                    .dexterity(8)
                    .strength(6)
                    .intelligence(10)
                    .race(hero.getRace())
                    .build();
        } catch (NullPointerException e) {
            return null;
        }
    }

    public CreateHeroResponse findByName(String name) {
        try {
            Hero hero = heroesMappedByName.get(name);
            return CreateHeroResponse
                    .builder()
                    .name(hero.getName())
                    .agility(5)
                    .dexterity(8)
                    .strength(6)
                    .intelligence(10)
                    .race(hero.getRace())
                    .build();
        } catch (NullPointerException e) {
            return null;
        }
    }

    public void updateHero (UUID id, UpdateHeroRequest updateHeroRequest) {
        heroesMappedById.get(id).setName(updateHeroRequest.getName());
        heroesMappedById.get(id).setRace(updateHeroRequest.getRace());
    }

    public void delete(UUID id) {
        heroesMappedById.remove(id);
    }
}
