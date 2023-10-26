package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.model.Hero;
import br.com.gubee.interview.model.request.UpdateHeroRequest;
import br.com.gubee.interview.model.response.CreateHeroResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class HeroRepositoryStub {

    Map<UUID, Hero> heroes = new HashMap<>();

    public UUID create(Hero hero) {
        try {
            heroes.put(hero.getId(), hero);
            return hero.getId();
        } catch (NullPointerException e) {
            return null;
        }
    }

    public CreateHeroResponse findById(UUID id) {
        try {
            Hero hero = heroes.get(id);
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
        heroes.get(id).setName(updateHeroRequest.getName());
        heroes.get(id).setRace(updateHeroRequest.getRace());
    }

    public void delete(UUID id) {
        heroes.remove(id);
    }
}
