package ru.robert_grammy.ine_markup.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import ru.robert_grammy.ine_markup.utils.files.FileManager;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopicsMapping {

    private final Map<String, Map<String, List<String>>> mapping = new HashMap<>();

    @JsonAnyGetter
    public Map<String, Map<String, List<String>>> getMapping() {
        return mapping;
    }

    @JsonAnySetter
    public void setSubtopics(String topic, Map<String, List<String>> subtopics) {
        mapping.put(topic, subtopics);
    }

    public Map<String, List<String>> getSubtopics(String topic) {
        return mapping.getOrDefault(topic, Map.of());
    }

    public static TopicsMapping load(URI path) throws IOException {
        return FileManager.getMapping(path, TopicsMapping.class);
    }

}
