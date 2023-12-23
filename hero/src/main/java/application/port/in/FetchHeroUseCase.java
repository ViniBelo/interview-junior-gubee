package application.port.in;

import dto.HeroDTO;

import java.util.List;
import java.util.UUID;

public interface FetchHeroUseCase {
    List<HeroDTO> getAll();

    HeroDTO findById(UUID id);
    HeroDTO findByName(String name);
}
