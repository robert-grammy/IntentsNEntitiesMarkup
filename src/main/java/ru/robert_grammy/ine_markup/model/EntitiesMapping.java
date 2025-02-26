package ru.robert_grammy.ine_markup.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import ru.robert_grammy.ine_markup.utils.files.FileManager;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntitiesMapping {

    private final Map<String, List<String>> mapping = new HashMap<>();

    @JsonAnyGetter
    public Map<String, List<String>> getMapping() {
        return mapping;
    }

    @JsonAnySetter
    public void setValuesToType(String type, List<String> values) {
        mapping.put(type, values);
    }

    public List<String> getValuesForType(String type) {
        return mapping.getOrDefault(type, List.of());
    }

    public static EntitiesMapping load(URI path) throws IOException {
        return FileManager.getMapping(path, EntitiesMapping.class);
    }

}
