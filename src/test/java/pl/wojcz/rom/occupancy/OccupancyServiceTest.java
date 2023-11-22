package pl.wojcz.rom.occupancy;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import pl.wojcz.rom.occupancy.type.OccupancyUsage;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OccupancyServiceTest {

    private OccupancyService service;

    @BeforeAll
    public void init() {
        service = new OccupancyService(new OccupancyRepository());
    }

    @Test
    public void taskTest01() {
       int premium = 3, economy = 3;
       System.out.println("Premium: "+premium+", Economy: "+economy);
       List<OccupancyUsage> result = service.check(premium, economy);

       for(OccupancyUsage r : result)
           System.out.println(r);

       assertThat(result).hasSize(2);

       assertThat(result.get(0).getQuantity()).isEqualTo(3);
       assertThat(result.get(0).getTotal()).isEqualTo(738);

       assertThat(result.get(1).getQuantity()).isEqualTo(3);
       assertThat(result.get(1).getTotal()).isEqualTo(167.99);
    }

    @Test
    public void taskTest02() {
        int premium = 7, economy = 5;
        System.out.println("Premium: "+premium+", Economy: "+economy);
        List<OccupancyUsage> result = service.check(premium, economy);

        for(OccupancyUsage r : result)
            System.out.println(r);

        assertThat(result).hasSize(2);

        assertThat(result.get(0).getQuantity()).isEqualTo(6);
        assertThat(result.get(0).getTotal()).isEqualTo(1054);

        assertThat(result.get(1).getQuantity()).isEqualTo(4);
        assertThat(result.get(1).getTotal()).isEqualTo(189.99);
    }

    @Test
    public void taskTest03() {
        int premium = 2, economy = 7;
        System.out.println("Premium: "+premium+", Economy: "+economy);
        List<OccupancyUsage> result = service.check(premium, economy);

        for(OccupancyUsage r : result)
            System.out.println(r);

        assertThat(result).hasSize(2);

        assertThat(result.get(0).getQuantity()).isEqualTo(2);
        assertThat(result.get(0).getTotal()).isEqualTo(583);

        assertThat(result.get(1).getQuantity()).isEqualTo(4);
        assertThat(result.get(1).getTotal()).isEqualTo(189.99);
    }

    @Test
    public void taskTest04() {
        int premium = 7, economy = 1;
        System.out.println("Premium: "+premium+", Economy: "+economy);
        List<OccupancyUsage> result = service.check(premium, economy);

        for(OccupancyUsage r : result)
            System.out.println(r);

        assertThat(result).hasSize(2);

        assertThat(result.get(0).getQuantity()).isEqualTo(7);
        assertThat(result.get(0).getTotal()).isEqualTo(1153.99);

        assertThat(result.get(1).getQuantity()).isEqualTo(1);
        assertThat(result.get(1).getTotal()).isEqualTo(45);
    }

}
