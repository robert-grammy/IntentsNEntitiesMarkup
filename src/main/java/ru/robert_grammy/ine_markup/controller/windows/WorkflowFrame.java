package ru.robert_grammy.ine_markup.controller.windows;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import ru.robert_grammy.ine_markup.INEApp;
import ru.robert_grammy.ine_markup.controller.ComponentController;
import ru.robert_grammy.ine_markup.controller.component.EntityRow;
import ru.robert_grammy.ine_markup.controller.global.AppController;
import ru.robert_grammy.ine_markup.model.*;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class WorkflowFrame implements ComponentController {

    public GridPane frame;
    public TextArea replicArea;
    public Button nextButton;
    public Button addEntityButton;
    public ComboBox<String> topic;
    public ComboBox<String> subTopic;
    public ComboBox<String> intent;
    public VBox entityList;

    private ReplicsList replics;
    private TopicsMapping topicsMapping;

    private boolean isInitialized = false;

    private final List<EntityRow> rows = new LinkedList<>();

    @Override
    public void initialize(AppController controller, Parent parent, int width, int height, String titleKey) {
        ComponentController.super.initialize(controller, parent, width, height, titleKey);
        topic.setOnAction(event -> {
            if (topic.getValue() != null) {
                resetSubtopicSelector();
            }
        });
        subTopic.setOnAction(event -> {
            if (subTopic.getValue() != null) {
                resetIntentSelector();
            }
        });
        intent.setOnAction(event -> {
            nextButton.setDisable(intent.getValue() == null);
        });
        replics = INEApp.getInstance().getController().getReplics();
        topicsMapping = INEApp.getInstance().getController().getTopicsMapping();
        try {
            nextReplic();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        isInitialized = true;
    }

    @FXML
    private void nextReplic() throws IOException {
        if (isInitialized) {
            List<Entity> entities = rows.stream().map(EntityRow::getEntity).toList();
            MarkupedReplic replic = new MarkupedReplic(replicArea.getText(), intent.getValue(), entities);
            INEApp.getInstance().getController().addReplic(replic);
        }
        if (replics.hasLines()) {
            replicArea.setText(replics.next());
            resetControls();
        } else {
            INEApp.getInstance().getController().setForm(AppController.FORM_LAST);
        }
    }

    private void resetControls() {
        resetTopicSelector();
        rows.clear();
        entityList.getChildren().clear();
    }

    private void resetTopicSelector() {
        topic.getSelectionModel().clearSelection();
        topic.getEditor().clear();
        topic.getItems().clear();
        topic.getItems().addAll(topicsMapping.getMapping().keySet());
        resetSubtopicSelector();
    }

    private void resetSubtopicSelector() {
        subTopic.getSelectionModel().clearSelection();
        subTopic.getItems().clear();
        if (topic.getValue() != null) {
            subTopic.getItems().addAll(topicsMapping.getMapping().get(topic.getValue()).keySet());
            subTopic.setDisable(false);
        } else {
            subTopic.setDisable(true);
        }
        resetIntentSelector();
    }

    private void resetIntentSelector() {
        intent.setValue(null);
        intent.getSelectionModel().clearSelection();
        intent.getItems().clear();
        if (subTopic.getValue() != null) {
            intent.getItems().addAll(topicsMapping.getMapping().get(topic.getValue()).get(subTopic.getValue()));
            intent.setDisable(false);
        } else {
            intent.setDisable(true);
        }
    }

    @FXML
    private void addEntity() throws IOException {
        String selected = replicArea.getSelectedText();
        if (selected == null || selected.isEmpty()) return;
        selected = selected.trim();
        final String finalSelected = selected;
        boolean alreadyIn = rows.stream().map(EntityRow::getEntity).map(Entity::text).anyMatch(text -> text.equalsIgnoreCase(finalSelected));
        if (alreadyIn) return;
        int start = replicArea.getSelection().getStart();
        int end = replicArea.getSelection().getEnd();
        replicArea.setEditable(false);
        EntityRow row = EntityRow.createEntityRow(selected, start, end, this);
        rows.add(row);
        entityList.getChildren().add(row.getBox());
    }

    public void removeEntity(EntityRow row) {
        entityList.getChildren().remove(row.getBox());
        rows.remove(row);
    }

}
