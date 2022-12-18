package ru.nsu.kalugin.fxasynclocationinformer;

import javafx.application.Platform;
import javafx.scene.control.TextArea;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class LocationWeatherParser {
    public void parse(ArrayList<Object> weatherInfo, TextArea outputArea) {
        outputArea.setText(outputArea.getText() + "\n-------------------\n");
        weatherInfo.forEach((info) -> {
            var prevText = outputArea.getText();
            if (info instanceof LinkedHashMap<?, ?>) {
                LinkedHashMap<String, Object> weatherHm = (LinkedHashMap<String, Object>) info;
                weatherHm.forEach((k, v) -> outputArea.setText(prevText + "\n" + k + ": " + String.valueOf(v)));
            } else if (info instanceof ArrayList<?>) {
                ArrayList<LinkedHashMap<String, Object>> weatherHm = (ArrayList<LinkedHashMap<String, Object>>) info;
                weatherHm.forEach((hm) -> hm.forEach((k, v) -> {
                    outputArea.setText(prevText + "\n" + k + ": " + String.valueOf(v));
                }));
            } else {
                outputArea.setText(prevText + "\n" + info);
            }
        });
    }
}
