package br.com.gubee.interview.model.response;

import br.com.gubee.interview.model.enums.Race;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PRIVATE)
public class ComparePowerStatsResponse {

    @NotNull(message = "message.powerstats.heroId1.mandatory")
    private UUID heroId1;

    @NotNull(message = "message.powerstats.heroId2.mandatory")
    private UUID heroId2;

    @Min(value = 0, message = "message.powerstats.strength.min")
    @Max(value = 10, message = "message.powerstats.strength.max")
    @NotNull(message = "message.powerstats.strength.mandatory")
    private int strength;

    @Min(value = 0, message = "message.powerstats.agility.min")
    @Max(value = 10, message = "message.powerstats.agility.max")
    @NotNull(message = "message.powerstats.agility.mandatory")
    private int agility;

    @Min(value = 0, message = "message.powerstats.dexterity.min")
    @Max(value = 10, message = "message.powerstats.dexterity.max")
    @NotNull(message = "message.powerstats.dexterity.mandatory")
    private int dexterity;

    @Min(value = 0, message = "message.powerstats.intelligence.min")
    @Max(value = 10, message = "message.powerstats.intelligence.max")
    @NotNull(message = "message.powerstats.intelligence.mandatory")
    private int intelligence;
}
