package ru.robert_grammy.ine_markup.intentsnentitiesmarkup;

import javafx.application.Application;
import javafx.stage.Stage;
import ru.robert_grammy.ine_markup.intentsnentitiesmarkup.controller.global.AppController;

public class INEApp extends Application {

    private static INEApp instance = new INEApp();

    private AppController controller;

    public static void main(String... args) {
        launch(args);
    }

    public static INEApp getInstance() {
        return instance;
    }

    private void setController(AppController controller) {
        this.controller = controller;
    }

    public AppController getController() {
        return controller;
    }

    @Override
    public void start(Stage stage) throws Exception {
        instance = this;
        controller = new AppController(stage);
        setController(controller);
        controller.initialize();
    }

}
