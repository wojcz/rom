package pl.wojcz.rom.occupancy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class OccupancyRepositoryTest {

    @Autowired
    private OccupancyRepository repository;

    @Test
    public void getPriceLimitShouldBe100() {
        assertThat(repository.getPriceLimit())
                .isEqualTo(100);
    }

    @Test
    public void getPricesSizeShouldBe10() {
        assertThat(repository.getPrices())
                .isNotEmpty()
                .hasSize(10);
    }
}
