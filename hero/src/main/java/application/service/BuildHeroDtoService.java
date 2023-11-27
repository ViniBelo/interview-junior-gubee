package application.service;

import application.port.in.BuildHeroDto;
import domain.model.Race;
import dto.HeroDTO;

import java.util.UUID;

public class BuildHeroDtoService implements BuildHeroDto {
    @Override
    public HeroDTO createHeroDto(String name, String race, UUID powerStatsId) {
        return new HeroDTO(
                name,
                Race.valueOf(race),
                powerStatsId
        );
    }
}
