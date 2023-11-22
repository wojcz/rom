package pl.wojcz.rom.occupancy;

import org.junit.jupiter.api.Test;
import pl.wojcz.rom.occupancy.type.OccupancyUsage;

import java.util.*;

public class OccupancyCheckTest {

    @Test
    public void mockTest() {
        //input
        double[] input = {23, 45, 155, 374, 22, 99.99, 100, 101, 115, 209};
        //double[] input = {154, 123, 103, 100, 100, 100, 182};
        //double[] input = {23, 56, 99, 83, 24, 56, 83};
        //double[] input = {23, 56, 99, 83, 24, 56, 83, 100, 100, 100, 99};
        //double[] input = {100, 200, 300, 100, 200, 300, 100, 200, 300};
        //double[] input = {99.95, 30, 50, 23, 86, 34, 95, 23, 18, 17};
        //int availablePremium = 3, availableEconomy = 3;
        int availablePremium = 7, availableEconomy = 5;
        //int availablePremium = 2, availableEconomy = 7;
        //int availablePremium = 7, availableEconomy = 1;
        //int availablePremium = 1, availableEconomy = 1;
        //int availablePremium = 0, availableEconomy = 1;
        double priceLimit = 100;

        //print output before calculation
        System.out.println(Arrays.toString(input));
        System.out.println("Premium: "+availablePremium+", Economy: "+availableEconomy);

        //prepare data
        Arrays.sort(input);
        Stack<Double> potentialGuests = new Stack<>();
        for(double p : input)
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

        //output
        System.out.println(usagePremium);
        System.out.println(usageEconomy);
    }

}
