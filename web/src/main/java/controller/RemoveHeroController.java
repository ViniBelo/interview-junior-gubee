package controller;

import adapter.HeroAdapter;
import adapter.PowerStatsAdapter;
import application.port.in.FetchHeroUseCase;
import application.port.in.RemoveHeroUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/api/v1/heroes", produces = MediaType.APPLICATION_JSON_VALUE)
public class RemoveHeroController {
    private final HeroAdapter heroAdapter;
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteHero(@Validated @PathVariable UUID id) {
        try {
            heroAdapter.deleteHero(id);
            return ResponseEntity.ok().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
