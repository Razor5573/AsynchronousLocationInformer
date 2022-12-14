package ru.nsu.kalugin.fxasynclocationinformer;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LocationWeather {
    private final Object coord;
    private final Object weather;
    private final Object base;
    private final Object main;
    private final Object visibility;
    private final Object wind;
    private final Object rain;
    private final Object clouds;
    private final Object dt;
    private final Object sys;
    private final Object timezone;
    private final Object id;
    private final Object name;
    private final Object cod;

    @JsonCreator
    public LocationWeather(
            @JsonProperty("coord")Object coord,
            @JsonProperty("weather") Object weather,
            @JsonProperty("base")Object  base,
            @JsonProperty("main")Object  main,
            @JsonProperty("visibility") Object visibility,
            @JsonProperty("wind")Object wind,
            @JsonProperty("rain") Object rain,
            @JsonProperty("clouds")Object clouds,
            @JsonProperty("dt")Object dt,
            @JsonProperty("sys") Object sys,
            @JsonProperty("timezone") Object timezone,
            @JsonProperty("id")Object id,
            @JsonProperty("name")Object name,
            @JsonProperty("cod") Object cod) {
        this.coord = coord;
        this.weather = weather;
        this.base = base;
        this.main = main;
        this.visibility = visibility;
        this.wind = wind;
        this.rain = rain;
        this.clouds = clouds;
        this.dt = dt;
        this.sys = sys;
        this.timezone = timezone;
        this.id = id;
        this.name = name;
        this.cod = cod;
    }

    public Object getCoord() {
        return coord;
    }

    public Object getWeather() {
        return weather;
    }

    public Object getBase() {
        return base;
    }

    public Object getMain() {
        return main;
    }

    public Object getVisibility() {
        return visibility;
    }

    public Object getWind() {
        return wind;
    }

    public Object getRain() {
        return rain;
    }

    public Object getClouds() {
        return clouds;
    }

    public Object getDt() {
        return dt;
    }

    public Object getSys() {
        return sys;
    }

    public Object getTimezone() {
        return timezone;
    }

    public Object getId() {
        return id;
    }

    public Object getName() {
        return name;
    }

    public Object getCod() {
        return cod;
    }
}