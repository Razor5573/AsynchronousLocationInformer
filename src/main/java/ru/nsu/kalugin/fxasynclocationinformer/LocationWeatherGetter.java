package ru.nsu.kalugin.fxasynclocationinformer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import static java.net.http.HttpRequest.newBuilder;

public class LocationWeatherGetter {
    private ArrayList<Object> fullWeatherInfo = new ArrayList<>();

    public CompletableFuture<LocationWeather> sendRequest(Double lat, Double lng) {
        String URLforRequest = "https://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon=" + lng + "&appid=1eb782cc0ba7aa0dd346cae143f7ce7a";
        HttpRequest request = null;
        try {
            request = newBuilder()
                    .uri(new URI(URLforRequest))
                    .GET()
                    .build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        HttpClient client = HttpClient.newBuilder().build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(json -> {
                    try {
                        return new ObjectMapper()
                                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                                .readValue(json, LocationWeather.class);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    return null;
                });
    }

    public ArrayList<Object> getFullWeatherInfo() {
        return fullWeatherInfo;
    }
}

