package ru.nsu.kalugin.fxasynclocationinformer;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.concurrent.CompletableFuture;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import static java.net.http.HttpRequest.newBuilder;

public class LocationInfoGetter {

    public CompletableFuture<LocationInfo> sendRequest(String location) {
        String URLforRequest = "https://graphhopper.com/api/1/geocode?q=" + location + "&key=a594ead4-e321-401b-b619-1baf4ba473fe";
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
                                 .readValue(json, LocationInfo.class);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    return null;
                });
    }
}
