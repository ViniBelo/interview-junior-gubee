package controller;

import adapter.HeroAdapter;
import application.port.in.*;
import dto.HeroDTO;
import dto.PowerStatsDto;
import org.springframework.http.MediaType;
import request.CreateHeroRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/api/v1/heroes", produces = MediaType.APPLICATION_JSON_VALUE)
public class UpdateHeroController {
    private final HeroAdapter heroAdapter;

    @PutMapping(value = "/{id}")
    public ResponseEntity<String> updateHero(@Validated @PathVariable UUID id, @Validated @RequestBody CreateHeroRequest createHeroRequest) {
        try {
            heroAdapter.updateById(id, createHeroRequest);
            return ResponseEntity.ok().body("Hero updated successfully");
        } catch (DuplicateKeyException e) {
            return ResponseEntity.badRequest().body("Duplicated hero");
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
