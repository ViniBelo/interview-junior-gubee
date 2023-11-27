package application.port.in;

import dto.HeroDTO;

import java.util.UUID;

public interface BuildHeroDto {
    public HeroDTO createHeroDto(String name, String race, UUID powerStatsId);
}
