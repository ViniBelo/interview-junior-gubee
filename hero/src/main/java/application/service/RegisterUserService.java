package application.service;

import application.port.in.RegisterHeroUseCase;
import application.port.out.CreateHeroPort;
import dto.HeroDTO;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class RegisterUserService implements RegisterHeroUseCase {
    private final CreateHeroPort createHeroPort;
    @Override
    public UUID create(HeroDTO heroDTO) {
        return createHeroPort.create(heroDTO);
    }
}
