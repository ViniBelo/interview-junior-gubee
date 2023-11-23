package application.port.in;

import dto.HeroDTO;

import java.util.UUID;

public interface RegisterHeroUseCase {
    UUID create(HeroDTO heroDTO);
}
