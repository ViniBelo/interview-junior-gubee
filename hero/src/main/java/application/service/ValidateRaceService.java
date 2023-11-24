package application.service;

import application.port.in.ValidateRaceUseCase;
import domain.model.Race;
import dto.HeroDTO;

public class ValidateRaceService implements ValidateRaceUseCase {
    @Override
    public String validRace(String race) {
        return Race.valueOf(race).toString();
    }
}
