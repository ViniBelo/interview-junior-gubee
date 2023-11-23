package application.service;

import application.port.in.RemoveHeroUseCase;
import application.port.out.DeleteHeroPort;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class RemoveHeroService implements RemoveHeroUseCase {
    private final DeleteHeroPort deleteHeroPort;
    @Override
    public void deleteHero(UUID id) {
        deleteHeroPort.deleteHero(id);
    }
}
