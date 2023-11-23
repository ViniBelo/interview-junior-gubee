package application.service;

import application.port.in.UpdateHeroUseCase;
import application.port.out.UpdateHeroPort;
import dto.HeroDTO;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class UpdateHeroService implements UpdateHeroUseCase {
    private final UpdateHeroPort updateHeroPort;
    @Override
    public void updateById(UUID id, HeroDTO heroDTO) {
        updateHeroPort.updateById(id, heroDTO);
    }
}
