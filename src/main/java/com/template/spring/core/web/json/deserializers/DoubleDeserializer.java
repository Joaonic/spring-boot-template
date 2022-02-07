package com.template.spring.core.web.json.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class DoubleDeserializer extends JsonDeserializer<Double> {

    @Override
    public Double deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {

        JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);

        String value = jsonNode.asText();

        if (value == null || value.isEmpty()) {
            return null;
        }

        String[] values = value.split(",");

        if (values.length == 1) {
            return Double.parseDouble(value);
        }

        String nonDecimalValue = values[0];
        String decimalValue;

        decimalValue = values[1];

        if (nonDecimalValue.length() > 3) {
            nonDecimalValue = nonDecimalValue.replaceAll("\\.", "");
        }

        value = nonDecimalValue + '.' + decimalValue;

        return Double.parseDouble(value);

    }

}
