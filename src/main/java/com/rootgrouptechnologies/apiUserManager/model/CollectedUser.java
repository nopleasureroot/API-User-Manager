package com.rootgrouptechnologies.apiUserManager.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import kong.unirest.json.JSONObject;

import java.io.IOException;

public class CollectedUser {
    public static class JsonObjectSerializer extends JsonSerializer<JSONObject> {

        @Override
        public void serialize(JSONObject jsonObject, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeRawValue(jsonObject.toString());
        }
    }

    @JsonSerialize(using = JsonObjectSerializer.class)
    private final JSONObject data;

    public CollectedUser(JSONObject data) {
        this.data = data;
    }

}
