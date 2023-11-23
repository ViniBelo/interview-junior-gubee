package application.service;

import application.port.in.FetchHeroUseCase;
import application.port.out.FindHeroPort;
import dto.HeroDTO;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class FetchHeroService implements FetchHeroUseCase {
    private final FindHeroPort findHeroPort;
    @Override
    public HeroDTO findById(UUID id) {
        return findHeroPort.findById(id);
    }

    @Override
    public HeroDTO findByName(String name) {
        return findHeroPort.findByName(name);
    }
}
