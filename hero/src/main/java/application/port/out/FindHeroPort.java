package application.port.out;

import dto.HeroDTO;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public interface FindHeroPort {
    List<HeroDTO> findAll();
    HeroDTO findById(UUID id);
    HeroDTO findByName(String name);
}
