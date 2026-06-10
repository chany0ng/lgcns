package com.lgcns.jpadsl.common;

import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ser.std.StdSerializer;

public class CardNoSerializer extends StdSerializer<String> {
    public CardNoSerializer() {
        super(String.class);
    }

    @Override
    public void serialize(String value, JsonGenerator gen, SerializationContext context) {
        if (value == null) {
            gen.writeNull();
            return;
        }
        gen.writeString(format(value.replaceAll("[\\s-]", "")));
    }

    private String format(String cardNo) {
        int start = 6;
        String ret = cardNo.substring(0, start) + "*".repeat(6);
        if (cardNo.length() > 12) {
            ret += cardNo.substring(start + 6);
        }

        return ret.replaceAll("(.{4})(?=.)", "$1-");
    }

}
