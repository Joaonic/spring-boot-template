package com.template.spring.core.web.json;

import flexjson.JSONSerializer;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;

@Component
public class RenderJsonConverter extends AbstractHttpMessageConverter<Json> {

    private final JsonObjectMapper customObjectMapper;

    public RenderJsonConverter(JsonObjectMapper customObjectMapper) {

        super(new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype()));

        this.customObjectMapper = customObjectMapper;

    }

    @Override
    protected boolean supports(Class<?> clazz) {

        return Json.class.isAssignableFrom(clazz);

    }

    @Override
    protected Json readInternal(Class<? extends Json> clazz, HttpInputMessage inputMessage) throws HttpMessageNotReadableException {

        return null;

    }

    @Override
    protected void writeInternal(Json json, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {

        OutputStream outputStream = outputMessage.getBody();
        JSONSerializer jsonSerializer = json.getJsonSerializer();
        Object object = json.getData();

        if (jsonSerializer != null) {

            outputStream.write(jsonSerializer.serialize(object).getBytes());

        } else {

            this.customObjectMapper.writeValue(outputStream, object);

        }

        outputStream.close();

    }

}
