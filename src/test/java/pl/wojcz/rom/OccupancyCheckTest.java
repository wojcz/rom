package pl.wojcz.rom;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class OccupancyCheckTest {

    @Test
    public void mockTest() {
        //input
        double[] potentialGuests = {23, 45, 155, 374, 22, 99.99, 100, 101, 115, 209};
        //double[] potentialGuests = {154, 123, 103, 100, 100, 100, 182};
        //double[] potentialGuests = {23, 56, 99, 83, 24, 56, 83};
        //double[] potentialGuests = {23, 56, 99, 83, 24, 56, 83, 100, 100, 100, 99};
        //int freePremium = 3, freeEconomy = 3;
        //int freePremium = 7, freeEconomy = 5;
        //int freePremium = 2, freeEconomy = 7;
        int freePremium = 7, freeEconomy = 1;
        double priceLimit = 100;

        System.out.println(Arrays.toString(potentialGuests));
        System.out.println("Premium{"+freePremium+"} Economy{"+freeEconomy+"}");

        //prepare data
        Arrays.sort(potentialGuests);
        Usage usagePremium = new Usage("Premium");
        Usage usageEconomy = new Usage("Economy");

        int splitIndex = -1;
        for(int i=potentialGuests.length-1; i>=0; i--) {
            if(potentialGuests[i] < priceLimit) {
                splitIndex = i;
                break;
            }
        }

        double[] economyClients = {};
        double[] premiumClients = potentialGuests;
        if(splitIndex >= 0) {
            economyClients = Arrays.copyOfRange(potentialGuests, 0, splitIndex+1);
            premiumClients = Arrays.copyOfRange(potentialGuests, splitIndex+1, potentialGuests.length);
        }

        //calculate premium
        for(int i=premiumClients.length-1; i>=0; i--) {
            if(freePremium <= 0)
                break;

            usagePremium.add(premiumClients[i]);
            --freePremium;
        }

        //calculate economy
        int freeEconomyFinal = freeEconomy;
        int clientsLeft;
        for(clientsLeft=economyClients.length-1; clientsLeft>=0; clientsLeft--) {
            if(freeEconomy <= 0)
                break;

            usageEconomy.add(economyClients[clientsLeft]);
            --freeEconomy;
        }
        if(freePremium > 0) {
            for (int i = clientsLeft+1; i >= 0; i--) {
                if(freePremium <= 0)
                    break;

                usageEconomy.remove(economyClients[i]);
                usagePremium.add(economyClients[i]);
                --freePremium;
            }
        }


//        for(int i=economyClients.length-1; i>=0; i--) {
//            if(freePremium > 0 && economyClients.length>freeEconomyFinal) {
//                usagePremium.add(economyClients[i]);
//                --freePremium;
//                continue;
//            }
//        }

        //output
        System.out.println(usagePremium);
        System.out.println(usageEconomy);
    }

    public static class Usage {
        private final String type;
        private int quantity;
        private double total;

        public Usage(String type) {
            this.type = type;
        }

        public void add(double price) {
            ++quantity;
            total+=price;
        }

        public void remove(double price) {
            --quantity;
            total-=price;
        }

        @Override
        public String toString() {
            return type+"{" +
                    "quantity=" + quantity +
                    ", total=" + total +
                    '}';
        }
    }

}
