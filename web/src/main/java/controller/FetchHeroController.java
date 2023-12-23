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
import org.springframework.web.bind.annotation.*;
import request.CreateHeroRequest;
import request.HeroResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/api/v1/heroes", produces = MediaType.APPLICATION_JSON_VALUE)
public class FetchHeroController {
    private final HeroAdapter heroAdapter;
    private final PowerStatsAdapter powerStatsAdapter;

    @GetMapping
    public ResponseEntity<List<HeroResponse>> findAll() {
        try {
            List<HeroDTO> heroes = heroAdapter.findAll();
            List<PowerStatsDto> powerStatsList = new ArrayList<>();
            List<HeroResponse> heroResponses = new ArrayList<>();
            for (HeroDTO hero: heroes) {
                PowerStatsDto powerStats = powerStatsAdapter.findPowerStatsById(hero.powerStatsId());
                HeroResponse createHeroResponse = createHeroResponse(hero, powerStats);
                heroResponses.add(createHeroResponse);
            }
            return ResponseEntity.ok(heroResponses);
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<HeroResponse> findById(@Validated @PathVariable UUID id) {
        try {
            HeroDTO hero = heroAdapter.findById(id);
            PowerStatsDto powerStats = powerStatsAdapter.findPowerStatsById(hero.powerStatsId());
            HeroResponse createHeroResponse = createHeroResponse(hero, powerStats);
            return ResponseEntity.ok(createHeroResponse);
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/search/{name}")
    public ResponseEntity<HeroResponse> findByName(@Validated @PathVariable String name) {
        try {
            HeroDTO hero = heroAdapter.findByName(name);
            PowerStatsDto powerStats = powerStatsAdapter.findPowerStatsById(hero.powerStatsId());
            HeroResponse createHeroResponse = createHeroResponse(hero, powerStats);
            return ResponseEntity.ok().body(createHeroResponse);
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.ok().build();
        }
    }

    private HeroResponse createHeroResponse (HeroDTO hero, PowerStatsDto powerStats) {
        return new HeroResponse(
                hero.id(),
                hero.name(),
                hero.race().name(),
                powerStats.strength(),
                powerStats.agility(),
                powerStats.dexterity(),
                powerStats.intelligence()
        );
    }
}
