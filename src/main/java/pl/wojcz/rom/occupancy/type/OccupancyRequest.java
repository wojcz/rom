package pl.wojcz.rom.occupancy.type;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class OccupancyRequest {

    @Min(0)
    @Digits(integer = 5, fraction = 0)
    @NotNull
    private Integer premium;

    @Min(0)
    @Digits(integer = 5, fraction = 0)
    @NotNull
    private Integer economy;

    public Integer getPremium() {
        return premium;
    }

    public Integer getEconomy() {
        return economy;
    }
}
