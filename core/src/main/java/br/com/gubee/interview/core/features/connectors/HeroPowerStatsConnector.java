package br.com.gubee.interview.core.features.connectors;

import br.com.gubee.interview.core.features.hero.HeroService;
import br.com.gubee.interview.core.features.powerstats.PowerStatsService;
import br.com.gubee.interview.model.Hero;
import br.com.gubee.interview.model.PowerStats;
import br.com.gubee.interview.model.request.CreateHeroRequest;
import br.com.gubee.interview.model.request.UpdateHeroRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;


@RequiredArgsConstructor
@Service
public class HeroPowerStatsConnector {

    private final PowerStatsService powerStatsService;
    private final HeroService heroService;

    @Transactional
    public Hero createHero(CreateHeroRequest createHeroRequest) {
        return new Hero(createHeroRequest, powerStatsService.create(
                new PowerStats(null, createHeroRequest.getStrength(),
                        createHeroRequest.getAgility(),
                        createHeroRequest.getDexterity(),
                        createHeroRequest.getIntelligence(), Instant.now(), Instant.now())));
    }

    @Transactional
    public void updateHeroAndStats(UUID id, UpdateHeroRequest updateHeroRequest) throws DuplicateKeyException, EmptyResultDataAccessException {
        powerStatsService.updateById(heroService.getPowerStatsIdFromCurrentHero(id), updateHeroRequest);
        heroService.updateById(id, updateHeroRequest);
    }

    @Transactional
    public void deleteHero(UUID id) throws EmptyResultDataAccessException {
        var powerStatsId = heroService.getPowerStatsIdFromCurrentHero(id);
        heroService.deleteHero(id);
        powerStatsService.deletePowerStats(powerStatsId);
    }
}
