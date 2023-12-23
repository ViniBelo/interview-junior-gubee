package application.service;

import application.port.in.FetchHeroUseCase;
import application.port.out.FindHeroPort;
import dto.HeroDTO;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class FetchHeroService implements FetchHeroUseCase {
    private final FindHeroPort findHeroPort;

    @Override
    public List<HeroDTO> getAll() {
        try {
            return findHeroPort.findAll();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public HeroDTO findById(UUID id) {
        try {
            return findHeroPort.findById(id);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public HeroDTO findByName(String name) {
        try {
            return findHeroPort.findByName(name);
        } catch (Exception e) {
            return null;
        }
    }
}
