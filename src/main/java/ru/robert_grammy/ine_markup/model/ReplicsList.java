package ru.robert_grammy.ine_markup.model;

import ru.robert_grammy.ine_markup.utils.files.FileManager;

import java.io.IOException;
import java.net.URI;

public class ReplicsList {

    private int currentPosition = 0;
    private final String[] replics;

    public ReplicsList(URI path) throws IOException {
        replics = FileManager.getLines(path);
    }

    public String next() {
        return replics[currentPosition++];
    }

    public boolean hasLines() {
        return currentPosition < replics.length;
    }

}