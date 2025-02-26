package ru.robert_grammy.ine_markup.intentsnentitiesmarkup.utils.files;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.net.URL;
import java.util.ResourceBundle;

public class FormLoader extends FXMLLoader {

    private static final ResourceBundle STRINGS = ResourceBundle.getBundle("locales/strings");
    private static final String GENERAL_STYLE = FileManager.getStylesheet("style");

    public static final FormLoader START_FRAME = new FormLoader(FileManager.getXMLViewURL("start_frame"));
    public static final FormLoader WORKFLOW_FRAME = new FormLoader(FileManager.getXMLViewURL("workflow_frame"));
    public static final FormLoader LAST_FRAME = new FormLoader(FileManager.getXMLViewURL("last_frame"));

    public FormLoader(URL form) {
        super(form);
        setResources(STRINGS);
    }

    public Scene createScene(Parent parent) {
        Scene scene = new Scene(parent, 1280, 720);
        scene.getStylesheets().add(GENERAL_STYLE);
        return scene;
    }

}
