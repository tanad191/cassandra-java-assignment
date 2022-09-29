package org.gcs.cassandra;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Running this functional test should return green for all endpoints
 * <p>
 * 1.  Start the local cassandra node
 * 2.  Start the cassandra-java-assignment application
 * 3.  Run this functional test
 * <p>
 * The endpoint names and json payload used here may not match exactly with what you chose.  Please feel free to change
 * this validation to accommodate your implementation.
 */
class ValidationFT {

    private static HttpClient httpClient;

    @BeforeAll
    public static void beforeAll() {
        httpClient = HttpClient.newBuilder().build();
    }

    @Test
    void satellite_reads_scan_location() throws Exception {
        String getUrl = "http://localhost:8080/scan/location/25N,71W"; // TODO Change if your endpoint is different
        String postUrl = "http://localhost:8080/scan/location"; // TODO Change if your endpoint is different

        String json = "" +
                "{" +
                "\"location\": \"25N,71W\"," +
                "\"name\": \"Bermuda Triangle\"" +
                "}";
        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(URI.create(postUrl))
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .header("Content-Type", "application/json")
                .build();
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(URI.create(getUrl))
                .GET()
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> postResponse = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
        HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());

        assertEquals(201, postResponse.statusCode());
        assertEquals(200, getResponse.statusCode());
        assertTrue(getResponse.body().contains("25N,71W"), "Response after saving a location should contain: '25N,71W'");
        assertTrue(getResponse.body().contains("Bermuda Triangle"), "Response after saving a location should contain: 'Bermuda Triangle'");
    }


    @Test
    void satellite_sends_scan_results() throws Exception {
        String getUrl = "http://localhost:8080/scan/result/location/25N,71W/day/2025-08-17"; // TODO Change if your endpoint is different
        String postUrl = "http://localhost:8080/scan/result"; // TODO Change if your endpoint is different

        String json = "" +
                "{" +
                "\"location\": \"25N,71W\"," +
                "\"scanDay\": \"2025-08-17\"," +
                "\"birdId\": \"50554d6e-29bb-11e5-b345-feff819cdc9f\"," +
                "\"birdSpecies\": \"Common loon\"," +
                "\"birdTraits\": [\"red eyes\", \"swim and dive\", \"webbed feet\"]" +
                "}";
        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(URI.create(postUrl))
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .header("Content-Type", "application/json")
                .build();
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(URI.create(getUrl))
                .GET()
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> postResponse = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
        HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());

        assertEquals(201, postResponse.statusCode());
        assertEquals(200, getResponse.statusCode());
        assertTrue(getResponse.body().contains("25N,71W"), "Response after saving a scan result should contain: '25N,71W'");
        assertTrue(getResponse.body().contains("2025-08-17"), "Response after saving a scan result should contain: '2025-08-17'");
        assertTrue(getResponse.body().contains("50554d6e-29bb-11e5-b345-feff819cdc9f"), "Response after saving a scan result should contain: '50554d6e-29bb-11e5-b345-feff819cdc9f'");
        assertTrue(getResponse.body().contains("Common loon"), "Response after saving a scan result should contain: 'Common loon'");
        assertTrue(getResponse.body().contains("red eyes"), "Response after saving a scan result should contain: 'red eyes'");
        assertTrue(getResponse.body().contains("swim and dive"), "Response after saving a scan result should contain: 'swim and dive'");
        assertTrue(getResponse.body().contains("webbed feet"), "Response after saving a scan result should contain: 'webbed feet'");
    }

}
