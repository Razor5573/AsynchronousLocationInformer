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

public class LocationDescriptionGetter {
    private ArrayList<Object> fullDescription = new ArrayList<>();

    public CompletableFuture<LocationDescription> sendRequest(String xid) {
        String URLforRequest = "http://api.opentripmap.com/0.1/en/places/xid/" + xid + "?apikey=5ae2e3f221c38a28845f05b6d0d11def7949695ae7dc4ff0c78702a2";
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
                                .readValue(json, LocationDescription.class);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    return null;
                });
    }

    public ArrayList<Object> getFullDescription() {
        return fullDescription;
    }
}
