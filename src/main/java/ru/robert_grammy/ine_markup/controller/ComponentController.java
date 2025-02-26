package ru.robert_grammy.ine_markup.controller;

import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import ru.robert_grammy.ine_markup.controller.global.AppController;
import ru.robert_grammy.ine_markup.utils.files.FileManager;

public interface ComponentController {

    default void initialize(AppController controller, Parent parent, int width, int height, String titleKey) {
        Stage stage = controller.getStage();
        controller.setTitle(FileManager.STRINGS.getString(titleKey));
        Rectangle2D screen = Screen.getPrimary().getBounds();
        stage.setX((screen.getWidth() - width) / 2);
        stage.setY((screen.getHeight() - height) / 2);
        Scene scene = new Scene(parent, width, height);
        stage.setResizable(false);
        scene.getStylesheets().add(FileManager.GENERAL_STYLE);
        stage.setScene(scene);
        stage.show();
    }

}
