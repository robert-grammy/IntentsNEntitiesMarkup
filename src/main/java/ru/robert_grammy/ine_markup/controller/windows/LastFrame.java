package ru.robert_grammy.ine_markup.controller.windows;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import ru.robert_grammy.ine_markup.INEApp;
import ru.robert_grammy.ine_markup.controller.ComponentController;
import ru.robert_grammy.ine_markup.controller.global.AppController;
import ru.robert_grammy.ine_markup.model.MarkupedReplic;
import ru.robert_grammy.ine_markup.utils.files.FileManager;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LastFrame implements ComponentController {


    public Label info;
    public ProgressBar progressBar;
    public Button closeButton;

    @Override
    public void initialize(AppController controller, Parent parent, int width, int height, String titleKey) {
        ComponentController.super.initialize(controller, parent, width, height, titleKey);
        Task<Void> task = saveFile();
        progressBar.progressProperty().bind(task.progressProperty());
        try (ExecutorService service = Executors.newSingleThreadExecutor()) {
            service.submit(task);
        }
    }

    private static String PART_OF_PATH = System.getProperty("user.home") + File.separator + "Desktop" + File.separator;
    public Task<Void> saveFile() {
        return new Task<>() {
            private static final String jsonFormat = "{\"correct\": [%s],\"incorrect\": [%s]}";

            @Override
            protected Void call() throws Exception {
                int size = INEApp.getInstance().getController().getMarkedReplics().size();
                List<String> parts = new ArrayList<>();
                List<String> incorrentParts = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    MarkupedReplic replic = INEApp.getInstance().getController().getMarkedReplics().get(i);
                    String json = FileManager.getJsonString(replic);
                    if (replic.isIncorrect()) {
                        incorrentParts.add(json);
                    } else {
                        parts.add(json);
                    }
                    updateProgress(i, size);
                }
                String fullJson = jsonFormat.formatted(String.join(",", parts), String.join(",", incorrentParts));
                String fileName = "result_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + ".json";
                Files.write(Paths.get(PART_OF_PATH + fileName), fullJson.getBytes());
                updateProgress(size, size);
                progressBar.setVisible(false);
                closeButton.setVisible(true);
                closeButton.setDisable(false);
                return null;
            }
        };
    }

    @FXML
    private void closeAndOpen() throws IOException {
        Desktop.getDesktop().open(new File(PART_OF_PATH));
        Platform.exit();
    }

}
