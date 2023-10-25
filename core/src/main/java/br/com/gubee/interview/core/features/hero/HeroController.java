package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.core.features.connectors.HeroPowerStatsConnector;
import br.com.gubee.interview.model.request.CreateHeroRequest;
import br.com.gubee.interview.model.request.UpdateHeroRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

import static java.lang.String.format;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.created;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/heroes", produces = APPLICATION_JSON_VALUE)
public class HeroController {

    private final HeroService heroService;
    private final HeroPowerStatsConnector heroPowerStatsConnector;

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@Validated
                                       @RequestBody CreateHeroRequest createHeroRequest) {
        final UUID id = heroService.create(createHeroRequest);
        return created(URI.create(format("/api/v1/heroes/%s", id))).build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> findById(@Validated @PathVariable UUID id) {
        return heroService.findById(id);
    }

    @GetMapping(value = "/search/{name}")
    public ResponseEntity<Object> findByName(@Validated @PathVariable String name) {
        return heroService.findByName(name);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateHero(@Validated @PathVariable UUID id, @Validated @RequestBody UpdateHeroRequest updateHeroRequest) {
        return heroPowerStatsConnector.updateHeroAndStats(id, updateHeroRequest);
    }
}
