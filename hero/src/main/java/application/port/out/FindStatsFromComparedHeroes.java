package application.port.out;

import dto.HeroDTO;

import java.util.UUID;
import java.util.Vector;

public interface FindStatsFromComparedHeroes {
    public Vector<HeroDTO> findUsersToCompare(UUID id1, UUID id2);
}
