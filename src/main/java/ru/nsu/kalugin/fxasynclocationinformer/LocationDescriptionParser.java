package ru.nsu.kalugin.fxasynclocationinformer;

import javafx.application.Platform;
import javafx.scene.control.TextArea;

import java.util.ArrayList;

public class LocationDescriptionParser {
    public void parse(ArrayList<Object> descriptionInfo, TextArea outputArea) {
        outputArea.setText(outputArea.getText() + "\n-------------------\n");

        descriptionInfo.forEach((info) -> {
            var prevText = outputArea.getText();
            if (info != null)
                outputArea.setText(prevText + "\n" + info.toString());

        });
    }
}
