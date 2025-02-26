package ru.robert_grammy.ine_markup.intentsnentitiesmarkup.controller.global;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.robert_grammy.ine_markup.intentsnentitiesmarkup.utils.files.FormLoader;

import java.io.IOException;

public class AppController {

    private static final String TITLE = "Intents N Entities Markup";

    public static final int FORM_START = 0;
    public static final int FORM_WORKFLOW = 1;
    public static final int FORM_LAST = 2;

    private final Stage stage;
    private Parent root;
    private FormLoader currentForm;
    private Scene currentScene;

    public AppController(Stage stage) {
        this.stage = stage;
    }

    public void initialize() throws IOException {
        setForm(FORM_WORKFLOW);
        stage.setTitle(TITLE);
        stage.show();
    }

    public void swapFormToStart() {
        currentForm = FormLoader.START_FRAME;
    }

    public void swapFormToWorkflow() {
        currentForm = FormLoader.WORKFLOW_FRAME;
    }

    public void swapFormToLast() {
        currentForm = FormLoader.LAST_FRAME;
    }

    public void rootReload() throws IOException {
        root = currentForm.load();
    }

    public void loadNewScene() {
        currentScene = currentForm.createScene(root);
    }

    public void swapStageScene() {
        stage.setScene(currentScene);
    }

    public void setForm(int typeCode) throws IOException {
        switch (typeCode) {
            case FORM_START: swapFormToStart(); break;
            case FORM_WORKFLOW: swapFormToWorkflow(); break;
            case FORM_LAST: swapFormToLast(); break;
            default: throw new IllegalArgumentException();
        }
        rootReload();
    }

}

