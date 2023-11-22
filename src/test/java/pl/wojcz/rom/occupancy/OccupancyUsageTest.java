package pl.wojcz.rom.occupancy;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import pl.wojcz.rom.occupancy.type.OccupancyUsage;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OccupancyUsageTest {

    private OccupancyUsage usage;

    @BeforeAll
    public void init() {
        usage = new OccupancyUsage("Test");
        usage.push(10.0);
        usage.push(40.0);
    }


    @Test
    public void totalIsSumOfPrices() {
        assertThat(usage.getTotal()).isEqualTo(50.0);
    }

    @Test
    public void quantityReturnsNumberOfPrices() {
        assertThat(usage.getQuantity()).isEqualTo(2);
    }

}
