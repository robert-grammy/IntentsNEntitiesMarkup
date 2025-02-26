module ru.robert_grammy.ine_markup.ine_markup {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires java.desktop;

    exports ru.robert_grammy.ine_markup;
    opens ru.robert_grammy.ine_markup to javafx.fxml;

    exports ru.robert_grammy.ine_markup.controller.windows;
    opens ru.robert_grammy.ine_markup.controller.windows to javafx.fxml;

    exports ru.robert_grammy.ine_markup.controller;
    opens ru.robert_grammy.ine_markup.controller to javafx.fxml;

    exports ru.robert_grammy.ine_markup.controller.global;
    opens ru.robert_grammy.ine_markup.controller.global to javafx.fxml;

    exports ru.robert_grammy.ine_markup.utils.files;
    opens ru.robert_grammy.ine_markup.utils.files to javafx.fxml;

    exports ru.robert_grammy.ine_markup.model;
    opens ru.robert_grammy.ine_markup.model to javafx.fxml;

    exports ru.robert_grammy.ine_markup.controller.component;
    opens ru.robert_grammy.ine_markup.controller.component to javafx.fxml;
}