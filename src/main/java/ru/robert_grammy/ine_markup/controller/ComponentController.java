package ru.robert_grammy.ine_markup.controller;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.robert_grammy.ine_markup.controller.global.AppController;
import ru.robert_grammy.ine_markup.utils.files.FileManager;

public interface FrameController {

    default void initialize(AppController controller, Parent parent, int width, int height, String titleKey) {
        Stage stage = controller.getStage();
        controller.setTitle(FileManager.STRINGS.getString(titleKey));
        Scene scene = new Scene(parent, width, height);
        //TODO добавить драг для мува окна и включить stage.initStyle(StageStyle.TRANSPARENT);
        stage.setResizable(false);
        scene.getStylesheets().add(FileManager.GENERAL_STYLE);
        stage.setScene(scene);
        stage.show();
    }

}
