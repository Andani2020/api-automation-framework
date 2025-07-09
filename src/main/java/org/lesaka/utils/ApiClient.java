package org.lesaka.utils;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class ApiClient {
    // Base URL for the API, fetched from configuration
    private static final String BASE_URL = ConfigManager.get("base.url");

    // Common request specification with headers
    private static RequestSpecification getRequestSpec() {
        return given()
                .baseUri(BASE_URL)
                .header("x-api-key", ConfigManager.get("api.key"))
                .contentType("application/json")
                .accept("*/*")
                .log().all();
    }

    // Generic GET method
    public static Response get(String endpoint) {
        return getRequestSpec()
                .when()
                .get(endpoint)
                .then()
                .log().all()  // logs response details
                .extract()
                .response();
    }

    // Generic POST method
    public static Response post(String endpoint, String jsonBody) {
        return getRequestSpec()
                .body(jsonBody) // REST Assured will serialize the Map as JSON
                .when()
                .post(endpoint)
                .then()
                .log().all()
                .extract().response();
    }

}