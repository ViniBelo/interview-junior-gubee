package application.port.out;

import dto.HeroDTO;

import java.util.UUID;

public interface FindHeroPort {
    HeroDTO findById(UUID id);
    HeroDTO findByName(String name);
}
