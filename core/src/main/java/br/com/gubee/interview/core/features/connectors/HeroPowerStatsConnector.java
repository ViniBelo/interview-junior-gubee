package br.com.gubee.interview.core.features.connectors;

import application.port.in.CreatePowerStatsUseCase;
import application.port.in.UpdatePowerStatsUseCase;
import br.com.gubee.interview.core.features.hero.HeroService;
import br.com.gubee.interview.model.request.CreateHeroRequest;
import br.com.gubee.interview.model.request.UpdateHeroRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HeroPowerStatsConnector {

    private final CreatePowerStatsUseCase powerStatsService;
    private final UpdatePowerStatsUseCase powerStatsService;
    private final HeroService heroService;

    @Transactional
    public UUID createHero(CreateHeroRequest createHeroRequest) {
        var powerStats = powerStatsService.create(createHeroRequest);
        return heroService.create(createHeroRequest, powerStats);
    }


    @Transactional
    public void updateHeroAndStats(UUID id, UpdateHeroRequest updateHeroRequest) throws DuplicateKeyException, EmptyResultDataAccessException {
        UUID powerStatsId = heroService.getPowerStatsIdFromCurrentHero(id);
        powerStatsService.updateById(powerStatsId, updateHeroRequest);
        heroService.updateById(id, updateHeroRequest);
    }

    @Transactional
    public void deleteHero(UUID id) throws EmptyResultDataAccessException {
        var powerStatsId = heroService.getPowerStatsIdFromCurrentHero(id);
        heroService.deleteHero(id);
        powerStatsService.deletePowerStats(powerStatsId);
    }
}

