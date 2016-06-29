package com.conflux.finflux.finflux.infrastructure;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Praveen J U on 6/29/2016.
 */
public class JsonDateSerializer implements JsonSerializer<Date> {
    @Override
    public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
        SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        String formattedTime = output.format(src);
        return new JsonPrimitive(formattedTime);
    }
}
