package com.template.spring.core.web.json.serializers;


import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import flexjson.transformer.DateTransformer;
import flexjson.transformer.Transformer;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateSerializer implements JsonSerializer<Date> {

    private static final String DATE_FORMAT_TIMETABLE = "dd/MM/yyyy HH:mm:ss.SSS";

    public static Transformer getTransformer(String dateFormat) {
        Locale.setDefault(new Locale("en", "US"));
        return new DateTransformer(dateFormat);
    }


    @Override
    public JsonElement serialize(Date date, Type arg1, JsonSerializationContext arg2) {

        SimpleDateFormat simpleDateFormatter = new SimpleDateFormat(DATE_FORMAT_TIMETABLE);

        return new JsonPrimitive(simpleDateFormatter.format(date));

    }
}
