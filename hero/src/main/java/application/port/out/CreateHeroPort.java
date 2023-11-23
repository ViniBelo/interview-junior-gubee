package application.port.out;

import dto.HeroDTO;

import java.util.UUID;

public interface CreateHeroPort {
    UUID create (HeroDTO heroDTO);
}
