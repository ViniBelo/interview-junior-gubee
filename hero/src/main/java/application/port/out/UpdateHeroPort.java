package application.port.out;

import dto.HeroDTO;

import java.util.UUID;

public interface UpdateHeroPort {
    void updateById(UUID id, HeroDTO heroDTO);
}
