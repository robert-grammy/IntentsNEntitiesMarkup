package ru.robert_grammy.ine_markup.utils.files;

import javafx.fxml.FXMLLoader;

import java.net.URL;

public class FormLoader extends FXMLLoader {

    public static final FormLoader START_FRAME = new FormLoader(FileManager.getXMLViewURL("start_frame"), 540, 280, "app.frame.start.title");
    public static final FormLoader WORKFLOW_FRAME = new FormLoader(FileManager.getXMLViewURL("workflow_frame"), 1280, 720, "app.frame.workflow.title");
    public static final FormLoader LAST_FRAME = new FormLoader(FileManager.getXMLViewURL("last_frame"), 500, 160, "app.frame.last.title");

    private final int width;
    private final int height;
    private final String titleKey;

    public FormLoader(URL form, int width, int height, String titleKey) {
        super(form);
        setResources(FileManager.STRINGS);
        this.width = width;
        this.height = height;
        this.titleKey = titleKey;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getTitleKey() {
        return titleKey;
    }
}
