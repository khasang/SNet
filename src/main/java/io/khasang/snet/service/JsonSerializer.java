package io.khasang.snet.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

public abstract class JsonSerializer<Entity> {
    private ObjectMapper mapper;
    private TypeReference<List<Entity>> typeReference;

    protected JsonSerializer() {
        this.mapper = new ObjectMapper();
        this.typeReference = new TypeReference<List<Entity>>(){};
    }


    public String parseToJson(Entity entity) {
        try {
            return mapper.writeValueAsString(entity);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    public String parseToJson(Collection<Entity> entity) {
        try {
            return mapper.writeValueAsString(entity);
        } catch (JsonProcessingException exc) {
            throw new RuntimeException(exc);
        }
    }


    public Entity parseToEntity(String json, Class<Entity> type) {
        try {
            return mapper.readValue(json, type);
        } catch (IOException exc) {
            throw new RuntimeException(exc);
        }
    }

    public Collection<Entity> parseToListEntity(String json) {
        try {
            return mapper.readValue(json,typeReference);
        } catch (IOException exc) {
            throw new RuntimeException(exc);
        }
    }
}
