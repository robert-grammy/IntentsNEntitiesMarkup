package ru.robert_grammy.ine_markup.controller.component;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import ru.robert_grammy.ine_markup.INEApp;
import ru.robert_grammy.ine_markup.controller.windows.WorkflowFrame;
import ru.robert_grammy.ine_markup.model.EntitiesMapping;
import ru.robert_grammy.ine_markup.model.Entity;
import ru.robert_grammy.ine_markup.utils.files.FileManager;

import java.io.IOException;

public class EntityRow {

    public Button deleteButton;
    public TextField selection;
    public ComboBox<String> entityType;
    public ComboBox<String> entityValue;

    EntitiesMapping entitiesMapping = INEApp.getInstance().getController().getEntitiesMapping();
    private WorkflowFrame controller;

    private String text;
    private int start, end;
    private HBox box;

    private void initialize(String text, int start, int end, HBox box, WorkflowFrame controller) {
        this.text = text;
        this.start = start;
        this.end = end;
        this.box = box;
        this.controller = controller;
        resetTypeSelector();
        entityType.setOnAction(event -> {
            if (entityType.getValue() != null) {
                resetValueSelector();
            }
        });
        selection.setEditable(false);
        selection.setText(text);
    }

    private void resetTypeSelector() {
        entityType.getSelectionModel().clearSelection();
        entityType.getEditor().clear();
        entityType.getItems().clear();
        entityType.getItems().addAll(entitiesMapping.getMapping().keySet());
        resetValueSelector();
    }

    private void resetValueSelector() {
        entityValue.getSelectionModel().clearSelection();
        entityValue.getItems().clear();
        if (entityType.getValue() != null) {
            entityValue.getItems().addAll(entitiesMapping.getMapping().get(entityType.getValue()));
            entityValue.setDisable(false);
        } else {
            entityValue.setDisable(true);
        }
    }

    public static EntityRow createEntityRow(String selection, int start, int end, WorkflowFrame controller) throws IOException {
        FXMLLoader loader = new FXMLLoader(FileManager.getXMLViewURL("entity_row_inline_component"));
        loader.setResources(FileManager.STRINGS);
        HBox line = loader.load();
        EntityRow row = loader.getController();
        row.initialize(selection, start, end, line, controller);
        return row;
    }

    public HBox getBox() {
        return box;
    }

    public Entity getEntity() {
        return new Entity(text, start, end, entityType.getValue(), entityValue.getValue());
    }

    @FXML
    public void delete() {
        controller.removeEntity(this);
    }

}