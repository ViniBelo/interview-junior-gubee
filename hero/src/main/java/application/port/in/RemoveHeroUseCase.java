package application.port.in;

import java.util.UUID;

public interface RemoveHeroUseCase {
    void deleteHero(UUID id);
}
