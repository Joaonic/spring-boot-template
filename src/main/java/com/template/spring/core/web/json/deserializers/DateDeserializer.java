package com.template.spring.core.web.json.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.template.spring.core.helpers.StringHelpers;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.util.Date;

public class DateDeserializer extends JsonDeserializer<Date> {

    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {

        JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);

        String date = jsonNode.asText();

        if (date == null || date.isEmpty()) {
            return null;
        }

        if (StringUtils.isNumeric(date) || date.startsWith("-")) {

            return new Date(Long.parseLong(date));

        } else if (date.matches("(\\d{4})-(\\d{2})-(\\d{2})T((\\d{2}):(\\d{2}):(\\d{2}))\\.(\\d{3})Z")) {

            return StringHelpers.parseDateISO(date);

        } else if (date.matches("(\\d{4})-(\\d{2})-(\\d{2})")) {

            return StringHelpers.parseDateIncompleteISO(date);

        } else if (date.matches("(\\d{4})-(\\d{2})-(\\d{2})T((\\d{2}):(\\d{2}):(\\d{2}))\\-(\\d{2}):(\\d{2})")) {

            return StringHelpers.parseDateISOTimezone(date);

        } else if (date.matches("(\\d{2}):(\\d{2})")) {

            return StringHelpers.parseTimeOnly(date);

        } else if (date.length() <= 10) {

            return StringHelpers.parseDate(date);

        } else {

            return StringHelpers.parseDateTimetable(date);

        }

    }

}
