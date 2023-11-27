package application.service;

import application.port.in.RegisterHeroUseCase;
import application.port.out.CreateHeroPort;
import dto.HeroDTO;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class RegisterHeroService implements RegisterHeroUseCase {
    private final CreateHeroPort createHeroPort;
    @Override
    public UUID create(HeroDTO heroDTO) {
        System.out.println("RHS");
        return createHeroPort.create(heroDTO);
    }
}
