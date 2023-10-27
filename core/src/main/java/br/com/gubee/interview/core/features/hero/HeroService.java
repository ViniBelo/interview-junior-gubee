package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.model.Hero;
import br.com.gubee.interview.model.request.CreateHeroRequest;
import br.com.gubee.interview.model.request.UpdateHeroRequest;
import br.com.gubee.interview.model.response.ComparePowerStatsResponse;
import br.com.gubee.interview.model.response.CreateHeroResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HeroService {

    private final HeroRepository heroRepository;

    @Transactional
    public UUID create(CreateHeroRequest createHeroRequest, UUID powerStatsId) {
        return heroRepository.create(new Hero(createHeroRequest, powerStatsId));
    }

    @Transactional
    public CreateHeroResponse findById(UUID id) throws EmptyResultDataAccessException {
        return heroRepository.findById(id);
    }

    @Transactional
    public CreateHeroResponse findByName(String name) throws EmptyResultDataAccessException {
        return heroRepository.findByName(name);
    }

    @Transactional
    public void updateById(UUID id, UpdateHeroRequest updateHeroRequest) throws DuplicateKeyException, EmptyResultDataAccessException {
        heroRepository.updateHero(id, updateHeroRequest);
    }

    @Transactional
    public UUID getPowerStatsIdFromCurrentHero(UUID id) {
        return heroRepository.getPowerStatsIdFromCurrentHero(id);
    }

    @Transactional
    public void deleteHero(UUID id) throws EmptyResultDataAccessException {
        heroRepository.delete(id);
    }

    @Transactional
    public ComparePowerStatsResponse compareHeroes(UUID heroId1, UUID heroId2) throws EmptyResultDataAccessException {
        CreateHeroResponse hero1 = heroRepository.findById(heroId1);
        CreateHeroResponse hero2 = heroRepository.findById(heroId2);
        return new ComparePowerStatsResponse(
                heroId1,
                heroId2,
                hero2.getStrength() - hero1.getStrength(),
                hero2.getAgility() - hero1.getAgility(),
                hero2.getDexterity() - hero1.getDexterity(),
                hero2.getIntelligence() - hero1.getIntelligence()
                );
    }
}
