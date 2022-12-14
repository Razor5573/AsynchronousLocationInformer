package ru.nsu.kalugin.fxasynclocationinformer;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Informer extends Application {
    static String location;
    LocationInfoGetter infoGetter = new LocationInfoGetter(); //all Getters and parsers
    LocationInfoParser infoParser = new LocationInfoParser();
    ArrayList<LinkedHashMap<String, Object>> fullInfo = new ArrayList<>();
    Integer took;

    LocationWeatherGetter weatherGetter = new LocationWeatherGetter();
    LocationWeatherParser weatherParser = new LocationWeatherParser();
    ArrayList<Object> fullWeatherInfo = new ArrayList<>();

    LocationInterestingPlacesGetter interestingPlacesGetter = new LocationInterestingPlacesGetter();
    LocationInterestingPlacesParser interestingPlacesParser = new LocationInterestingPlacesParser();
    ArrayList<Object> fullInterestingPlacesInfo = new ArrayList<>();

    LocationDescriptionGetter descriptionGetter = new LocationDescriptionGetter();
    LocationDescriptionParser descriptionParser = new LocationDescriptionParser();
    ArrayList<Object> fullDescription = new ArrayList<>();

    public static void main(String[] args) {
        Application.launch();
    }

    @FXML
    TextArea outputArea = new TextArea();

    @FXML
    TextField locationField;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Informer.class.getResource("app-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 800);
        stage.setTitle("Asynchronous location informer");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void onEnterPress(){
        location = locationField.getText();
        Platform.runLater(() -> {
            outputArea.setText("Your location: " + location);
        });
        locationField.clear();
    }

    @FXML
    protected void onFirstRequestClick() {
        var fullInfo = infoGetter.sendRequest(location);
        fullInfo.thenAccept((locationInfo) -> {
            this.fullInfo.addAll(locationInfo.getHits());
            took = locationInfo.getTook();
            Platform.runLater(() -> {
                infoParser.parse(this.fullInfo, outputArea);
                outputArea.setText(outputArea.getText() + "\n took: " + took);
            });
        });
    }

    @FXML
    protected void onSecondRequestClick() {
        var weather = weatherGetter.sendRequest(infoParser.getLat(), infoParser.getLng());
        weather.thenAccept((weatherInfo) -> {
            fullWeatherInfo.add(weatherInfo.getCoord());
            fullWeatherInfo.add(weatherInfo.getWeather());
            fullWeatherInfo.add(weatherInfo.getBase());
            fullWeatherInfo.add(weatherInfo.getMain());
            fullWeatherInfo.add(weatherInfo.getVisibility());
            fullWeatherInfo.add(weatherInfo.getWind());
            fullWeatherInfo.add(weatherInfo.getRain());
            fullWeatherInfo.add(weatherInfo.getClouds());
            fullWeatherInfo.add(weatherInfo.getDt());
            fullWeatherInfo.add(weatherInfo.getSys());
            fullWeatherInfo.add(weatherInfo.getTimezone());
            fullWeatherInfo.add(weatherInfo.getName());
            fullWeatherInfo.add(weatherInfo.getCod());
            Platform.runLater(() -> {
                weatherParser.parse(fullWeatherInfo, outputArea);
            });
        });

    }

    @FXML
    protected void onThirdRequestClick() {
        var interestingPlaces = interestingPlacesGetter.sendRequest(infoParser.getLat(), infoParser.getLng());
        interestingPlaces.thenAccept(interestingPlacesInfo -> {
            fullInterestingPlacesInfo.add(interestingPlacesInfo.getFeatures());
            Platform.runLater(() -> {
                interestingPlacesParser.parse(fullInterestingPlacesInfo, outputArea);
            });
        });

    }


    @FXML
    protected void onFourthRequestClick() {
        var descriptionInfo = descriptionGetter.sendRequest(interestingPlacesParser.getXid());
        descriptionInfo.thenAccept(description -> {
            fullDescription.add(description.getImage());
            fullDescription.add(description.getInfo());
            fullDescription.add(description.getName());
            fullDescription.add(description.getRate());
            fullDescription.add(description.getWikidata());
            fullDescription.add(description.getWikipedia());
        });
        Platform.runLater(() -> {
            descriptionParser.parse(fullDescription, outputArea);
        });
    }


}
