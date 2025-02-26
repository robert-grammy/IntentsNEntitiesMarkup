package ru.robert_grammy.ine_markup.utils.files;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.robert_grammy.ine_markup.model.MarkupedReplic;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Stream;

public final class FileManager {

    public static final ResourceBundle STRINGS = ResourceBundle.getBundle("locales/strings");
    public static final String GENERAL_STYLE = FileManager.getStylesheet("style");

    private FileManager() {}

    private static final String XML_VIEW_PATH_FORMAT = "/fxml/%s.fxml";
    public static URL getXMLViewURL(String name) {
        return FileManager.class.getResource(XML_VIEW_PATH_FORMAT.formatted(name));
    }

    private static final String STYLE_PATH_FORMAT = "/css/%s.css";
    public static String getStylesheet(String name) {
        return Objects.requireNonNull(FileManager.class.getResource(STYLE_PATH_FORMAT.formatted(name))).toExternalForm();
    }

    public static String[] getLines(URI path) throws IOException {
        String[] array;
        try (Stream<String> stream = Files.lines(Paths.get(path))) {
            array = stream.distinct().map(String::toLowerCase).toArray(String[]::new);
        }
        return array;
    }

    private static final ObjectMapper mapper = new ObjectMapper();
    public static <T> T getMapping(URI path, Class<T> type) throws IOException {
        return mapper.readValue(path.toURL(), type);
    }

    public static String getJsonString(MarkupedReplic replic) throws JsonProcessingException {
        return mapper.writer().writeValueAsString(replic);
    }

    public static InputStream getInputStream(String path) {
        return FileManager.class.getResourceAsStream(path);
    }

}
