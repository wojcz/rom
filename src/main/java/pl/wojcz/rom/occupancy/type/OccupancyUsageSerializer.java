package pl.wojcz.rom.occupancy.type;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class OccupancyUsageSerializer extends StdSerializer<OccupancyUsage> {

    public OccupancyUsageSerializer() {
        super(OccupancyUsage.class);

    }

    @Override
    public void serialize(OccupancyUsage obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("type", obj.getType());
        jsonGenerator.writeNumberField("quantity", obj.getQuantity());
        jsonGenerator.writeNumberField("total", obj.getTotal());
        jsonGenerator.writeEndObject();
    }
}
