package adapter;

import application.port.in.*;
import dto.HeroDTO;
import lombok.AllArgsConstructor;
import request.CreateHeroRequest;

import java.util.UUID;

@AllArgsConstructor
public class HeroAdapter {
    private final FetchHeroUseCase fetchHeroUseCase;
    private final UpdateHeroUseCase updateHeroUseCase;
    private final RemoveHeroUseCase removeHeroUseCase;
    private final RegisterHeroUseCase registerHeroUseCase;
    private final PowerStatsAdapter powerStatsAdapter;

    public UUID create(CreateHeroRequest createHeroRequest) {
        final UUID powerStatsId = powerStatsAdapter.create(createHeroRequest);
        HeroDTO heroDTO = createHeroDTO(createHeroRequest, powerStatsId);
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
        HeroDTO hero = createHeroDTO(createHeroRequest, powerStatsId);
        updateHeroUseCase.updateById(id, hero);
    }

    private HeroDTO createHeroDTO (CreateHeroRequest createHeroRequest, UUID powerStatsId) {
        return new HeroDTO(
                createHeroRequest.getName(),
                createHeroRequest.getRace(),
                powerStatsId
        );
    }

    public void deleteHero(UUID id) {
        var powerStatsId = fetchHeroUseCase.findById(id).powerStatsId();
        removeHeroUseCase.deleteHero(id);
        powerStatsAdapter.deletePowerStats(powerStatsId);
    }
}
