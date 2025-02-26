package ru.robert_grammy.ine_markup.controller.windows;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ru.robert_grammy.ine_markup.INEApp;
import ru.robert_grammy.ine_markup.controller.ComponentController;
import ru.robert_grammy.ine_markup.controller.global.AppController;
import ru.robert_grammy.ine_markup.utils.files.FileManager;

import java.io.File;
import java.io.IOException;
import java.net.URI;

public class StartFrame implements ComponentController {

    public GridPane frame;
    public Button replicsLoadButton;
    public Button entityMappingLoadButton;
    public Button intentMappingLoadButton;
    public Button continueButton;

    private static final FileChooser fileChooser = new FileChooser();

    static {
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("*", "*.txt", "*.json"),
                new FileChooser.ExtensionFilter(FileManager.STRINGS.getString("app.frame.start.file_chooser.extension_descriptor.txt"), "*.txt"),
                new FileChooser.ExtensionFilter(FileManager.STRINGS.getString("app.frame.start.file_chooser.extension_descriptor.json"), "*.json")
        );
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home"), "Desktop"));
    }

    public static final int REPLICS_CHOOSER = 0;
    public static final int TOPICS_MAPPING_CHOOSER = 1;
    public static final int ENTITIES_MAPPING_CHOOSER = 2;

    private URI replics, entitiesMapping, topicsMapping;

    private boolean isAllSelected() {
        return replics != null && topicsMapping != null && entitiesMapping != null;
    }

    private boolean loadFiles() {
        if (!isAllSelected()) return false;
        try {
            INEApp.getInstance().getController().loadSettings(replics, topicsMapping, entitiesMapping);
        } catch (IOException e) {
            System.getLogger(INEApp.class.getName()).log(System.Logger.Level.ERROR, e.getMessage());
            return false;
        }
        return true;
    }

    private void fileChoose(int typeCode) {
        switch (typeCode) {
            case REPLICS_CHOOSER:
                replics = setFileChooserSettingsAndShowDialog("app.frame.start.file_chooser.open.replics", replicsLoadButton);
                break;
            case TOPICS_MAPPING_CHOOSER:
                topicsMapping = setFileChooserSettingsAndShowDialog("app.frame.start.file_chooser.open.intent_mapping", intentMappingLoadButton);
                break;
            case ENTITIES_MAPPING_CHOOSER:
                entitiesMapping = setFileChooserSettingsAndShowDialog("app.frame.start.file_chooser.open.entity_mapping", entityMappingLoadButton);
                break;
            default:
                throw new IllegalArgumentException();
        }
        continueButton.setDisable(!isAllSelected());
    }

    private URI setFileChooserSettingsAndShowDialog(String textKey, Button button) {
        fileChooser.setTitle(FileManager.STRINGS.getString(textKey));
        Stage stage = INEApp.getInstance().getController().getStage();
        File file = fileChooser.showOpenDialog(stage);
        if (file != null && file.exists()) {
            button.setText(file.getName());
            return file.toURI();
        } else {
            return null;
        }
    }

    @FXML
    private void openReplics() {
        fileChoose(REPLICS_CHOOSER);
    }

    @FXML
    private void openTopicsMapping() {
        fileChoose(TOPICS_MAPPING_CHOOSER);
    }

    @FXML
    private void openEntityMapping() {
        fileChoose(ENTITIES_MAPPING_CHOOSER);
    }

    @FXML
    private void nextFrame() throws IOException {
        boolean isLoadSuccess = loadFiles();
        if (isLoadSuccess) {
            INEApp.getInstance().getController().setForm(AppController.FORM_WORKFLOW);
        } else {
            showErrorAlert();
            resetButtons();
            unsetModels();
        }
    }

    private void showErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(FileManager.STRINGS.getString("app.frame.start.alert.title"));
        alert.setHeaderText(FileManager.STRINGS.getString("app.frame.start.alert.header"));
        alert.setContentText(FileManager.STRINGS.getString("app.frame.start.alert.content"));
        alert.showAndWait();
    }

    private void resetButtons() {
        continueButton.setDisable(true);
        replicsLoadButton.setText(FileManager.STRINGS.getString("app.frame.start.button.load_replics"));
        entityMappingLoadButton.setText(FileManager.STRINGS.getString("app.frame.start.button.load_entity_mapping"));
        intentMappingLoadButton.setText(FileManager.STRINGS.getString("app.frame.start.button.load_intent_mapping"));
    }

    private void unsetModels() {
        replics = null;
        topicsMapping = null;
        entitiesMapping = null;
    }

}
