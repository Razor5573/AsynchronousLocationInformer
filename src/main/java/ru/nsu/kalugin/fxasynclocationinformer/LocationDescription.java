package ru.nsu.kalugin.fxasynclocationinformer;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LocationDescription {
    private final Object name;
    private final Object wikipedia;
    private final Object image;
    private final Object wikidata;
    private final Object rate;
    private final Object info;

    @JsonCreator
    public LocationDescription(
            @JsonProperty("name") Object name,
            @JsonProperty("wikipedia") Object wikipedia,
            @JsonProperty("image") Object image,
            @JsonProperty("wikidata") Object wikidata,
            @JsonProperty("rate") Object rate,
            @JsonProperty("info") Object info){
        this.name = name;
        this.wikipedia = wikipedia;
        this.image = image;
        this.wikidata = wikidata;
        this.rate = rate;
        this.info = info;
    }

    public Object getName() {
        return name;
    }

    public Object getWikipedia() {
        return wikipedia;
    }

    public Object getImage() {
        return image;
    }

    public Object getWikidata() {
        return wikidata;
    }

    public Object getRate() {
        return rate;
    }

    public Object getInfo() {
        return info;
    }
}
