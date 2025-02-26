package ru.robert_grammy.ine_markup.intentsnentitiesmarkup.utils.files;

import java.net.URL;
import java.util.Objects;

public final class FileManager {

    private FileManager() {}

    private static final String XML_VIEW_PATH_FORMAT = "/fxml/%s.fxml";
    public static URL getXMLViewURL(String name) {
        return FileManager.class.getResource(XML_VIEW_PATH_FORMAT.formatted(name));
    }

    private static final String STYLE_PATH_FORMAT = "/css/%s.css";
    public static String getStylesheet(String name) {
        return Objects.requireNonNull(FileManager.class.getResource(STYLE_PATH_FORMAT.formatted(name))).toExternalForm();
    }

}
