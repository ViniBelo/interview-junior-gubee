package controller;

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
@RequestMapping(value = "/api/v1/heroes", produces = MediaType.APPLICATION_JSON_VALUE)
public class UpdateHeroController {
    private final FetchHeroUseCase fetchHeroUseCase;
    private final UpdateHeroUseCase updateHeroUseCase;
    private final UpdatePowerStatsUseCase updatePowerStatsUseCase;

    @PutMapping(value = "/{id}")
    public ResponseEntity<String> updateHero(@Validated @PathVariable UUID id, @Validated @RequestBody CreateHeroRequest createHeroRequest) {
        try {
            UUID powerStatsId = fetchHeroUseCase.findById(id).powerStatsId();
            PowerStatsDto powerStats = createPowerStatsDTO(createHeroRequest);
            updatePowerStatsUseCase.updateById(powerStatsId, powerStats);
            HeroDTO hero = createHeroDTO(createHeroRequest, powerStatsId);
            updateHeroUseCase.updateById(id, hero);
            return ResponseEntity.ok().body("Hero updated successfully");
        } catch (DuplicateKeyException e) {
            return ResponseEntity.badRequest().body("Duplicated hero");
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }

    private PowerStatsDto createPowerStatsDTO (CreateHeroRequest createHeroRequest) {
        return new PowerStatsDto(
                createHeroRequest.getStrength(),
                createHeroRequest.getAgility(),
                createHeroRequest.getDexterity(),
                createHeroRequest.getIntelligence()
        );
    }

    private HeroDTO createHeroDTO (CreateHeroRequest createHeroRequest, UUID powerStatsId) {
        return new HeroDTO(
                createHeroRequest.getName(),
                createHeroRequest.getRace(),
                powerStatsId
        );
    }
}
