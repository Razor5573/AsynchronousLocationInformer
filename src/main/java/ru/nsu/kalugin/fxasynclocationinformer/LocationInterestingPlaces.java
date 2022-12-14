package ru.nsu.kalugin.fxasynclocationinformer;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class LocationInterestingPlaces {
    private final ArrayList<Object> features;
    @JsonCreator
    public LocationInterestingPlaces(
            @JsonProperty("features") ArrayList<Object> features){
        this.features = features;
    }

    public ArrayList<Object> getFeatures() {
        return features;
    }
}
