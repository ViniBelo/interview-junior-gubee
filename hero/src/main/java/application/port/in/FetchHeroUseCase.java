package application.port.in;

import dto.HeroDTO;

import java.util.UUID;

public interface FetchHeroUseCase {
    HeroDTO findById(UUID id);
    HeroDTO findByName(String name);
}
