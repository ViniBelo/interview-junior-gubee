package application.port.in;

import dto.HeroDTO;

import java.util.UUID;

public interface UpdateHeroUseCase {
    void updateById(UUID id, HeroDTO heroDTO);
}
