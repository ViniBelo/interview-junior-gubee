package controller;

import adapter.PowerStatsAdapter;
import application.port.in.FetchHeroUseCase;
import application.port.in.RemoveHeroUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/heroes", produces = MediaType.APPLICATION_JSON_VALUE)
public class RemoveHeroController {
    private final FetchHeroUseCase fetchHeroUseCase;
    private final RemoveHeroUseCase removeHeroUseCase;
    private final PowerStatsAdapter powerStatsAdapter;
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteHero(@Validated @PathVariable UUID id) {
        try {
            var powerStatsId = fetchHeroUseCase.findById(id).powerStatsId();
            removeHeroUseCase.deleteHero(id);
            powerStatsAdapter.deletePowerStats(powerStatsId);
            return ResponseEntity.ok().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
