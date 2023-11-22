package pl.wojcz.rom.occupancy;

import org.springframework.stereotype.Service;
import pl.wojcz.rom.occupancy.type.OccupancyUsage;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

@Service
public class OccupancyService {

    private final OccupancyRepository repository;

    public OccupancyService(OccupancyRepository repository) {
        this.repository = repository;
    }

    public List<OccupancyUsage> check(int availablePremium, int availableEconomy) {
        double[] prices = repository.getPrices();
        double priceLimit = repository.getPriceLimit();

        //prepare data
        Arrays.sort(prices);
        Stack<Double> potentialGuests = new Stack<>();
        for(double p : prices)
            potentialGuests.push(p);

        //init output objects
        OccupancyUsage usagePremium = new OccupancyUsage("Premium");
        OccupancyUsage usageEconomy = new OccupancyUsage("Economy");

        //calculate
        while(!potentialGuests.isEmpty()) {
            double price = potentialGuests.pop();

            //got available premium rooms for this price
            if(availablePremium > 0 && price >= priceLimit) {
                usagePremium.push(price);
                --availablePremium;
                continue;
            }

            //got available economy rooms for this price
            if(availableEconomy > 0 && price < priceLimit) {
                usageEconomy.push(price);
                --availableEconomy;
                continue;
            }

            //if we got available premium room for economy guests -> upgrade
            if(availableEconomy == 0 && availablePremium > 0 && !usageEconomy.isEmpty()) {
                usagePremium.push(usageEconomy.get(0)); //not using pop in order to get the highest price for upgrade
                usageEconomy.remove(0);
                usageEconomy.push(price);
                --availablePremium;
            }
        }

        return List.of(usagePremium, usageEconomy);
    }

}
