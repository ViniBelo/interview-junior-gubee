package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.core.features.connectors.HeroPowerStatsConnector;
import br.com.gubee.interview.model.request.CreateHeroRequest;
import br.com.gubee.interview.model.request.UpdateHeroRequest;
import br.com.gubee.interview.model.response.ComparePowerStatsResponse;
import br.com.gubee.interview.model.response.CreateHeroResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

import static java.lang.String.format;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/heroes", produces = APPLICATION_JSON_VALUE)
public class HeroController {

    private final HeroService heroService;
    private final HeroPowerStatsConnector heroPowerStatsConnector;

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@Validated @RequestBody CreateHeroRequest createHeroRequest) {
        final UUID id = heroPowerStatsConnector.createHero(createHeroRequest);
        return created(URI.create(format("/api/v1/heroes/%s", id))).build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CreateHeroResponse> findById(@Validated @PathVariable UUID id) {
        try {
            CreateHeroResponse createHeroResponse = heroService.findById(id);
            return ok(createHeroResponse);
        } catch (EmptyResultDataAccessException e) {
            return notFound().build();
        }
    }

    @GetMapping(value = "/search/{name}")
    public ResponseEntity<CreateHeroResponse> findByName(@Validated @PathVariable String name) {
        try {
            CreateHeroResponse createHeroResponse = heroService.findByName(name);
            return ok().body(createHeroResponse);
        } catch (EmptyResultDataAccessException e) {
            return ok().build();
        }
    }

    @GetMapping(value = "/{heroId1}/{heroId2}")
    public ResponseEntity<ComparePowerStatsResponse> compareById(@Validated @PathVariable UUID heroId1, @Validated @PathVariable UUID heroId2) {
        try {
            ComparePowerStatsResponse heroResponse = heroService.compareHeroes(heroId1, heroId2);
            return ok().body(heroResponse);
        } catch (EmptyResultDataAccessException e) {
            return notFound().build();
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<String> updateHero(@Validated @PathVariable UUID id, @Validated @RequestBody UpdateHeroRequest updateHeroRequest) {
        try {
            heroPowerStatsConnector.updateHeroAndStats(id, updateHeroRequest);
            return ok().body("Hero updated successfully");
        } catch (DuplicateKeyException e) {
            return badRequest().body("Duplicated hero");
        } catch (EmptyResultDataAccessException e) {
            return notFound().build();
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteHero(@Validated @PathVariable UUID id) {
        try {
            heroPowerStatsConnector.deleteHero(id);
            return ok().build();
        } catch (EmptyResultDataAccessException e) {
            return notFound().build();
        }
    }
}
