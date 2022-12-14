package ru.nsu.kalugin.fxasynclocationinformer;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class LocationInfo {
    private final int took;
    private final ArrayList <LinkedHashMap<String, Object>> hits;

    @JsonCreator
    public LocationInfo(
            @JsonProperty("hits") ArrayList <LinkedHashMap<String, Object>> hits,
            @JsonProperty("took") int took) {
        this.hits = hits;
        this.took = took;
    }

    public Integer getTook() {
        return took;
    }

    public ArrayList <LinkedHashMap<String, Object>> getHits() {
        return hits;
    }
}

