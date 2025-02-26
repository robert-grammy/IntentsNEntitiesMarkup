package ru.robert_grammy.ine_markup.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public record MarkupedReplic(String text, String intent, List<Entity> entities) {

    @JsonIgnore
    public boolean isIncorrect() {
        boolean textAndIntentAreCorrect = text == null || text.isEmpty() || intent == null || intent.isEmpty();
        boolean entitiesIsCorrect = true;
        for (Entity entity : entities) {
            if (entity.text() == null || entity.text().isEmpty() ||
                    entity.type() == null || entity.type().isEmpty() ||
                    entity.value() == null || entity.value().isEmpty()
            ) {
                entitiesIsCorrect = false;
                break;
            }
        }
        return textAndIntentAreCorrect && entitiesIsCorrect;
    }

}
