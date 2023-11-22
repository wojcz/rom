package pl.wojcz.rom.occupancy.type;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Stack;

@JsonSerialize(using = OccupancyUsageSerializer.class)
public class OccupancyUsage extends Stack<Double> {

    private final String type;

    public OccupancyUsage(String type) {
        super();
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public Double getTotal() {
        return stream().mapToDouble(Double::doubleValue).sum();
    }

    public int getQuantity() {
        return size();
    }

    @Override
    public String toString() {
        return type+"{" +
                "quantity=" + getQuantity() +
                ", total=" + getTotal() +
                '}';
    }
}
