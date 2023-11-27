package controller;

import adapter.HeroAdapter;
import adapter.PowerStatsAdapter;
import dto.HeroDTO;
import dto.PowerStatsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import request.CreateHeroRequest;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/heroes", produces = MediaType.APPLICATION_JSON_VALUE)
public class FetchHeroController {
    private final HeroAdapter heroAdapter;
    private final PowerStatsAdapter powerStatsAdapter;
    @GetMapping(value = "/{id}")
    public ResponseEntity<CreateHeroRequest> findById(@Validated @PathVariable UUID id) {
        try {
            HeroDTO hero = heroAdapter.findById(id);
            PowerStatsDto powerStats = powerStatsAdapter.findPowerStatsById(hero.powerStatsId());
            CreateHeroRequest createHeroResponse = createHeroRequest(hero, powerStats);
            return ResponseEntity.ok(createHeroResponse);
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/search/{name}")
    public ResponseEntity<CreateHeroRequest> findByName(@Validated @PathVariable String name) {
        try {
            HeroDTO hero = heroAdapter.findByName(name);
            PowerStatsDto powerStats = powerStatsAdapter.findPowerStatsById(hero.powerStatsId());
            CreateHeroRequest createHeroResponse = createHeroRequest(hero, powerStats);
            return ResponseEntity.ok().body(createHeroResponse);
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.ok().build();
        }
    }

    private CreateHeroRequest createHeroRequest (HeroDTO hero, PowerStatsDto powerStats) {
        return new CreateHeroRequest(
                hero.name(),
                hero.race().name(),
                powerStats.strength(),
                powerStats.agility(),
                powerStats.dexterity(),
                powerStats.intelligence()
        );
    }
}
