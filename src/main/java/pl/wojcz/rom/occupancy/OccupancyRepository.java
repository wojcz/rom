package pl.wojcz.rom.occupancy;

import org.springframework.stereotype.Repository;

@Repository
public class OccupancyRepository {

    public double[] getPrices() {
        return new double[]{23, 45, 155, 374, 22, 99.99, 100, 101, 115, 209};
    }

    public double getPriceLimit() {
        return 100;
    }

}
