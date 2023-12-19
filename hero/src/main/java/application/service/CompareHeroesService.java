package application.service;

import application.port.in.CompareHeroStats;
import application.port.out.FindStatsFromComparedHeroes;
import dto.HeroDTO;
import lombok.RequiredArgsConstructor;

import java.util.UUID;
import java.util.Vector;

@RequiredArgsConstructor
public class CompareHeroesService implements CompareHeroStats {
    private FindStatsFromComparedHeroes findStatsFromComparedHeroes;

    @Override
    public Vector<HeroDTO> heroesToCompare(UUID id1, UUID id2) {
        return findStatsFromComparedHeroes.findUsersToCompare(id1, id2);
    }
}
