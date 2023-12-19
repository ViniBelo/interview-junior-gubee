package controller;

import adapter.HeroAdapter;
import adapter.PowerStatsAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import response.CompareStatsResponse;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/api/v1/heroes", produces = MediaType.APPLICATION_JSON_VALUE)
public class CompareHeroesController {
    private final HeroAdapter heroAdapter;

    @GetMapping(value = "/{id1}/{id2}")
    public ResponseEntity<CompareStatsResponse> compareHeroes(@Validated @PathVariable UUID id1, @Validated @PathVariable UUID id2) {
        try {
            CompareStatsResponse compareStatsResponse = heroAdapter.compareHeroes(id1, id2);
            return ResponseEntity.ok(compareStatsResponse);
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
