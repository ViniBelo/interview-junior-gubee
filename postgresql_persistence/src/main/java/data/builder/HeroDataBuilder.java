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

import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PRIVATE)
public class HeroDataBuilder {

    @NotNull
    private UUID id;

    @NotBlank
    @Length(min = 1, max = 255)
    private String name;

    @NotNull
    private String race;

    @NotNull
    private UUID powerStatsId;
}
