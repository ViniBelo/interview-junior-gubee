package controller;

import adapter.HeroAdapter;
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
    private final HeroAdapter heroAdapter;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@Validated @RequestBody CreateHeroRequest createHeroRequest) {
        UUID heroId = heroAdapter.create(createHeroRequest);
        return ResponseEntity.created(URI.create(String.format("/api/v1/heroes/%s", heroId))).build();
    }
}
