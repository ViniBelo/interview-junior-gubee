package br.com.gubee.interview.core.features.connectors;

import br.com.gubee.interview.core.features.hero.HeroService;
import br.com.gubee.interview.core.features.powerstats.PowerStatsService;
import br.com.gubee.interview.model.Hero;
import br.com.gubee.interview.model.PowerStats;
import br.com.gubee.interview.model.request.CreateHeroRequest;
import br.com.gubee.interview.model.request.UpdateHeroRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;


@RequiredArgsConstructor
@Service
public class HeroPowerStatsConnector {

    private final PowerStatsService powerStatsService;
    private final HeroService heroService;

    public Hero createHero(CreateHeroRequest createHeroRequest) {
        return new Hero(createHeroRequest, powerStatsService.create(
                new PowerStats(null, createHeroRequest.getStrength(),
                        createHeroRequest.getAgility(),
                        createHeroRequest.getDexterity(),
                        createHeroRequest.getIntelligence(), Instant.now(), Instant.now())));
    }

    public ResponseEntity<Object> updateHeroAndStats(UUID id, UpdateHeroRequest updateHeroRequest) {
        try {
            powerStatsService.updateById(heroService.getPowerStatsIdFromCurrentHero(id), updateHeroRequest);
            return heroService.updateById(id, updateHeroRequest);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Fail updating hero", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Object> deleteHero(UUID id) {
        try {
            var powerStatsId = heroService.getPowerStatsIdFromCurrentHero(id);
            heroService.deleteHero(id);
            powerStatsService.deletePowerStats(powerStatsId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Fail updating hero", HttpStatus.BAD_REQUEST);
        }
    }
}
