package data.builder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PRIVATE)
public class PowerStatsDataBuilder {

    @Min(value = 0)
    @Max(value = 10)
    @NotNull
    private int strength;

    @Min(value = 0)
    @Max(value = 10)
    private int agility;

    @Min(value = 0)
    @Max(value = 10)
    @NotNull
    private int dexterity;

    @Min(value = 0)
    @Max(value = 10)
    @NotNull
    private int intelligence;
}
