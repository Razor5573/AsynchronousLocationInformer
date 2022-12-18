package ru.nsu.kalugin.fxasynclocationinformer;

import javafx.application.Platform;
import javafx.scene.control.TextArea;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class LocationInfoParser {
    private double lat;
    private double lng;

    public void parse (ArrayList<LinkedHashMap<String, Object>> locInfo, TextArea outputArea){
        locInfo.forEach((hm) ->
            hm.forEach((hit, value) -> {

                if("point".equals(hit)){
                    var prevText = outputArea.getText();
                    LinkedHashMap<String, Double> latLng = (LinkedHashMap<String, Double>) hm.get(hit);
                    lat = latLng.get("lat");
                    lng = latLng.get("lng");
                        outputArea.setText(prevText + "\npoint: " +
                                "\n    lattitude: " + lat +
                                "    longtittude: " + lng);
                }
                else if("extent".equals(hit)){
                    var prevText = outputArea.getText();
                    ArrayList<Double> extent = (ArrayList<Double>) hm.get(hit);
                        outputArea.setText(prevText + "\n    extent: ");
                    extent.forEach((val)-> outputArea.setText(prevText + "\n    extent: " + String.valueOf(val)));
                }
                else{
                    var prevText = outputArea.getText();
                    outputArea.setText(prevText + "\n" + hit + ": " + value);
                }
            })
        );
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }
}
