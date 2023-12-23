package adapter;

import application.port.in.*;
import application.port.out.FindStatsFromComparedHeroes;
import dto.HeroDTO;
import dto.PowerStatsDto;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import request.CreateHeroRequest;
import response.CompareStatsResponse;

import java.util.List;
import java.util.UUID;
import java.util.Vector;

@Service
@RequiredArgsConstructor
public class HeroAdapter {
    private final FetchHeroUseCase fetchHeroUseCase;
    private final UpdateHeroUseCase updateHeroUseCase;
    private final RemoveHeroUseCase removeHeroUseCase;
    private final RegisterHeroUseCase registerHeroUseCase;
    private final FindStatsFromComparedHeroes findStatsFromComparedHeroes;
    private final BuildHeroDto buildHeroDto;
    private final PowerStatsAdapter powerStatsAdapter;

    public UUID create(CreateHeroRequest createHeroRequest) {
        final UUID powerStatsId = powerStatsAdapter.create(createHeroRequest);
        HeroDTO heroDTO = buildHeroDto.createHeroDto(
                createHeroRequest.getName(),
                createHeroRequest.getRace(),
                powerStatsId
        );
        return registerHeroUseCase.create(heroDTO);
    }

    public CompareStatsResponse compareHeroes(UUID id1, UUID id2) {
        Vector<HeroDTO> heroes = findStatsFromComparedHeroes.findUsersToCompare(id1, id2);
        PowerStatsDto powerStats1 = powerStatsAdapter.findPowerStatsById(heroes.get(0).powerStatsId());
        PowerStatsDto powerStats2 = powerStatsAdapter.findPowerStatsById(heroes.get(1).powerStatsId());
        return CompareStatsResponse.builder()
                .id1(heroes.get(0).powerStatsId())
                .id2(heroes.get(0).powerStatsId())
                .strength(powerStats1.strength() - powerStats2.strength())
                .agility(powerStats1.agility() - powerStats2.agility())
                .dexterity(powerStats1.dexterity() - powerStats2.dexterity())
                .intelligence(powerStats1.intelligence() - powerStats2.intelligence())
                .build();
    }

    public List<HeroDTO> findAll() {
        return fetchHeroUseCase.getAll();
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
