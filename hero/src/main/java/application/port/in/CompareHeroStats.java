package application.port.in;

import dto.HeroDTO;

import java.util.UUID;
import java.util.Vector;

public interface CompareHeroStats {
    Vector<HeroDTO> heroesToCompare(UUID id1, UUID id2);
}
