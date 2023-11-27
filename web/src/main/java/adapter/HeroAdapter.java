package adapter;

import application.port.in.*;
import dto.HeroDTO;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import request.CreateHeroRequest;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HeroAdapter {
    private final FetchHeroUseCase fetchHeroUseCase;
    private final UpdateHeroUseCase updateHeroUseCase;
    private final RemoveHeroUseCase removeHeroUseCase;
    private final RegisterHeroUseCase registerHeroUseCase;
    private final BuildHeroDto buildHeroDto;
    private final PowerStatsAdapter powerStatsAdapter;

    public UUID create(CreateHeroRequest createHeroRequest) {
        System.out.println("RHA");
        final UUID powerStatsId = powerStatsAdapter.create(createHeroRequest);
        HeroDTO heroDTO = buildHeroDto.createHeroDto(
                createHeroRequest.getName(),
                createHeroRequest.getRace(),
                powerStatsId
        );
        return registerHeroUseCase.create(heroDTO);
    }

    public HeroDTO findById(UUID id) {
        return fetchHeroUseCase.findById(id);
    }

    public HeroDTO findByName(String name) {
        return fetchHeroUseCase.findByName(name);
    }

    public void updateById(UUID id, CreateHeroRequest createHeroRequest) {
        UUID powerStatsId = fetchHeroUseCase.findById(id).powerStatsId();
        powerStatsAdapter.updateById(powerStatsId, createHeroRequest);
        HeroDTO hero = buildHeroDto.createHeroDto(
                createHeroRequest.getName(),
                createHeroRequest.getRace(),
                powerStatsId
        );
        updateHeroUseCase.updateById(id, hero);
    }

    public void deleteHero(UUID id) {
        var powerStatsId = fetchHeroUseCase.findById(id).powerStatsId();
        removeHeroUseCase.deleteHero(id);
        powerStatsAdapter.deletePowerStats(powerStatsId);
    }
}
