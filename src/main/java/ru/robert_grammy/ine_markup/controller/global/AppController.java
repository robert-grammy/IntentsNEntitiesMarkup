package ru.robert_grammy.ine_markup.controller.global;

import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import ru.robert_grammy.ine_markup.controller.ComponentController;
import ru.robert_grammy.ine_markup.model.EntitiesMapping;
import ru.robert_grammy.ine_markup.model.MarkupedReplic;
import ru.robert_grammy.ine_markup.model.ReplicsList;
import ru.robert_grammy.ine_markup.model.TopicsMapping;
import ru.robert_grammy.ine_markup.utils.files.FileManager;
import ru.robert_grammy.ine_markup.utils.files.FormLoader;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class AppController {

    private static final String TITLE_FORMAT = FileManager.STRINGS.getString("app.title") + " - %s";

    public static final int FORM_START = 0;
    public static final int FORM_WORKFLOW = 1;
    public static final int FORM_LAST = 2;

    private final Stage stage;
    private ReplicsList replics;
    private TopicsMapping topicsMapping;
    private EntitiesMapping entitiesMapping;

    private List<MarkupedReplic> markeds = new ArrayList<>();

    public AppController(Stage stage) {
        this.stage = stage;
    }

    public void initialize() throws IOException {
        stage.getIcons().add(new Image(FileManager.getInputStream("/icons/icon.png")));
        setForm(FORM_START);
    }

    public void setTitle(String subtitle) {
        stage.setTitle(TITLE_FORMAT.formatted(subtitle));
    }

    private void swap(FormLoader loader) throws IOException {
        Parent parent = loader.load();
        ComponentController componentController = loader.getController();
        componentController.initialize(this, parent, loader.getWidth(), loader.getHeight(), loader.getTitleKey());
    }

    public void setForm(int typeCode) throws IOException {
        switch (typeCode) {
            case FORM_START:
                swap(FormLoader.START_FRAME);
                break;
            case FORM_WORKFLOW:
                swap(FormLoader.WORKFLOW_FRAME);
                break;
            case FORM_LAST:
                swap(FormLoader.LAST_FRAME);
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    public void loadSettings(URI replicsFileURI, URI topicMappingFileURI, URI entityMappingFileURI) throws IOException {
        replics = new ReplicsList(replicsFileURI);
        topicsMapping = TopicsMapping.load(topicMappingFileURI);
        entitiesMapping = EntitiesMapping.load(entityMappingFileURI);
    }

    public ReplicsList getReplics() {
        return replics;
    }

    public TopicsMapping getTopicsMapping() {
        return topicsMapping;
    }

    public EntitiesMapping getEntitiesMapping() {
        return entitiesMapping;
    }

    public Stage getStage() {
        return stage;
    }

    public void addReplic(MarkupedReplic replic) {
        markeds.add(replic);
    }

    public List<MarkupedReplic> getMarkedReplics() {
        return markeds;
    }

}

