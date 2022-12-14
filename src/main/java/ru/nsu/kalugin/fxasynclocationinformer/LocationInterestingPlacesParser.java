package ru.nsu.kalugin.fxasynclocationinformer;

import javafx.application.Platform;
import javafx.scene.control.TextArea;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class LocationInterestingPlacesParser {
    String xid;

    public void parse(ArrayList<Object> interestingPlacesInfo, TextArea outputArea) {
        var count = new Object() {
            int i = 1;
        };
        outputArea.setText(outputArea.getText() + "\n-------------------\n");
        interestingPlacesInfo.forEach((fullInfo) -> {
            var prevText = outputArea.getText();
            ArrayList<LinkedHashMap<String, Object>> arrayInfo = (ArrayList<LinkedHashMap<String, Object>>) fullInfo;
            arrayInfo.forEach((hm) -> {
                hm.forEach((strKey, value) -> {
                    if ("properties".equals(strKey)) {
                        if (count.i > 5) {
                            return;
                        }
                        outputArea.setText(prevText + "\nProperty " + count.i + ": ");

                        count.i++;
                        LinkedHashMap<String, Object> properties = (LinkedHashMap<String, Object>) value;
                        properties.forEach((property, propValue) -> {
                            if ("xid".equals(property)) {
                                this.xid = (String) propValue;
                            }
                            outputArea.setText(prevText + "\n" + property + ": " + propValue);
                        });
                    }
                });
            });
        });
    }

    public String getXid() {
        return xid;
    }
}
