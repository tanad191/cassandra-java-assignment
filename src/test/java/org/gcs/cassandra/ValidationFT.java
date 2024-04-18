package org.gcs.cassandra;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;

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
    	String[] args = {};
		SpringApplication.run(CassandraJavaAssignment.class, args);
    }

    @Test
    void satellite_reads_scan_location() throws Exception {
		
        String getUrl = "http://localhost:8080/api/ScanLocations/Coordinates/25N,71W"; // TODO Change if your endpoint is different
        String postUrl = "http://localhost:8080/api/ScanLocations"; // TODO Change if your endpoint is different

        String json = "" +
                "{" +
                "\"resultId\": \"0bc9b5d5-c1d4-4e56-bad9-bf89a15c8f58\"," +
                "\"coordinates\": \"25N,71W\"," +
                "\"name\": \"Bermuda Triangle\"" +
                "}";
        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(URI.create(postUrl))
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .header("Content-Type", "application/json")
                .build();
        HttpRequest getRequest1 = HttpRequest.newBuilder()
                .uri(URI.create(postUrl))
                .GET()
                .header("Content-Type", "application/json")
                .build();
        HttpRequest getRequest2 = HttpRequest.newBuilder()
                .uri(URI.create(getUrl))
                .GET()
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> postResponse = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
        HttpResponse<String> get1Response = httpClient.send(getRequest1, HttpResponse.BodyHandlers.ofString());
        HttpResponse<String> get2Response = httpClient.send(getRequest2, HttpResponse.BodyHandlers.ofString());

        System.out.println("Test 1 POST result: " + postResponse.body());
        System.out.println("Test 1 GET 1 result: " + get1Response.body());
        System.out.println("Test 1 GET 2 result: " + get2Response.body());

        assertEquals(201, postResponse.statusCode());
        assertEquals(200, get1Response.statusCode());
        assertEquals(200, get2Response.statusCode());
        
        assertTrue(get1Response.body().contains("25N,71W"), "Response after saving a location should contain: '25N,71W'");
        assertTrue(get1Response.body().contains("Bermuda Triangle"), "Response after saving a location should contain: 'Bermuda Triangle'");

        assertTrue(get2Response.body().contains("25N,71W"), "Response when retrieving a location should contain: '25N,71W'");
        assertTrue(get2Response.body().contains("Bermuda Triangle"), "Response when retrieving a location should contain: 'Bermuda Triangle'");
    }


    @Test
    void satellite_sends_scan_results() throws Exception {
		
        String getUrl = "http://localhost:8080/api/ScanResults/Coordinates/25N,71W/ScanDay/2025-08-17"; // TODO Change if your endpoint is different
        String postUrl = "http://localhost:8080/api/ScanResults"; // TODO Change if your endpoint is different

        String json = "" +
                "{" +
                "\"resultId\": \"0bc9b5d5-c1d4-4e56-bad9-bf89a15c8f58\"," +
                "\"coordinates\": \"25N,71W\"," +
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
        HttpRequest getRequest1 = HttpRequest.newBuilder()
                .uri(URI.create(postUrl))
                .GET()
                .header("Content-Type", "application/json")
                .build();
        HttpRequest getRequest2 = HttpRequest.newBuilder()
                .uri(URI.create(getUrl))
                .GET()
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> postResponse = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
        HttpResponse<String> get1Response = httpClient.send(getRequest1, HttpResponse.BodyHandlers.ofString());
        HttpResponse<String> get2Response = httpClient.send(getRequest2, HttpResponse.BodyHandlers.ofString());

        System.out.println("Test 2 POST result: " + postResponse.body());
        System.out.println("Test 2 GET 1 result: " + get1Response.body());
        System.out.println("Test 2 GET 2 result: " + get2Response.body());

        assertEquals(201, postResponse.statusCode());
        assertEquals(200, get1Response.statusCode());
        assertEquals(200, get2Response.statusCode());
        
        assertTrue(get1Response.body().contains("25N,71W"), "Response after saving a scan result should contain: '25N,71W'");
        assertTrue(get1Response.body().contains("2025-08-17"), "Response after saving a scan result should contain: '2025-08-17'");
        assertTrue(get1Response.body().contains("50554d6e-29bb-11e5-b345-feff819cdc9f"), "Response after saving a scan result should contain: '50554d6e-29bb-11e5-b345-feff819cdc9f'");
        assertTrue(get1Response.body().contains("Common loon"), "Response after saving a scan result should contain: 'Common loon'");
        assertTrue(get1Response.body().contains("red eyes"), "Response after saving a scan result should contain: 'red eyes'");
        assertTrue(get1Response.body().contains("swim and dive"), "Response after saving a scan result should contain: 'swim and dive'");
        assertTrue(get1Response.body().contains("webbed feet"), "Response after saving a scan result should contain: 'webbed feet'");
        
        assertTrue(get2Response.body().contains("25N,71W"), "Response when retrieving a scan result should contain: '25N,71W'");
        assertTrue(get2Response.body().contains("2025-08-17"), "Response when retrieving a scan result should contain: '2025-08-17'");
        assertTrue(get2Response.body().contains("50554d6e-29bb-11e5-b345-feff819cdc9f"), "Response when retrieving a scan result should contain: '50554d6e-29bb-11e5-b345-feff819cdc9f'");
        assertTrue(get2Response.body().contains("Common loon"), "Response when retrieving a scan result should contain: 'Common loon'");
        assertTrue(get2Response.body().contains("red eyes"), "Response when retrieving a scan result should contain: 'red eyes'");
        assertTrue(get2Response.body().contains("swim and dive"), "Response when retrieving a scan result should contain: 'swim and dive'");
        assertTrue(get2Response.body().contains("webbed feet"), "Response when retrieving a scan result should contain: 'webbed feet'");
    }

}
