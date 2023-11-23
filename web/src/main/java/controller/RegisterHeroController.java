package controller;

import adapter.PowerStatsAdapter;
import application.port.in.CreatePowerStatsUseCase;
import application.port.in.RegisterHeroUseCase;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import request.CreateHeroRequest;
import dto.HeroDTO;
import dto.PowerStatsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.URI;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/heroes", produces = MediaType.APPLICATION_JSON_VALUE)
public class RegisterHeroController {
    private final RegisterHeroUseCase registerHeroUseCase;
    private final PowerStatsAdapter powerStatsAdapter;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@Validated @RequestBody CreateHeroRequest createHeroRequest) {
        final UUID powerStatsId = powerStatsAdapter.create(createHeroRequest);
        HeroDTO heroDTO = createHeroDTO(createHeroRequest, powerStatsId);
        final UUID heroId = registerHeroUseCase.create(heroDTO);
        return ResponseEntity.created(URI.create(String.format("/api/v1/heroes/%s", heroId))).build();
    }

    private HeroDTO createHeroDTO (CreateHeroRequest createHeroRequest, UUID powerStatsId) {
        return new HeroDTO(
                createHeroRequest.getName(),
                createHeroRequest.getRace(),
                powerStatsId
        );
    }
}
