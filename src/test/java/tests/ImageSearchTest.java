package tests;
import org.lesaka.utils.ConfigManager;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.lesaka.utils.ApiClient;
import org.testng.annotations.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Epic("Cat API")
@Feature("Images Endpoint")
public class ImageSearchTest {

    @Story("GET /images/search validation")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test GET /images/search and validate response status, URL format, and schema")
    @Test
    public void testImageSearch() {
        String endpoint = resolvePath();

        logRequestPath(endpoint);

        Response response = ApiClient.get(endpoint);
        int status = response.getStatusCode();

        logResponseStatus(status);

        assertEquals(status, 200, "Expected 200 OK");

        String imageUrl = response.jsonPath().getString("[0].url");
        assertTrue(imageUrl != null && imageUrl.startsWith("http"), "Image URL should be valid");

        validateSchema(response);
    }

    @Step("Resolving API path from config key: {key}")
    private String resolvePath() {
        return ConfigManager.get("images.search.endpoint");
    }

    @Step("Request sent to path: {path}")
    private void logRequestPath(String path) {
        // Allure log step
        Allure.step("Request sent to path: " + path);
        System.out.println("Request sent to path: " + path);
    }

    @Step("Response status received: {status}")
    private void logResponseStatus(int status) {
        // Allure log step
        Allure.step("Response status received: " + status);
        System.out.println("Response status received: " + status);
    }

    @Step("Validating response against JSON schema: image-search-schema.json")
    private void validateSchema(Response response) {
        response.then().assertThat()
                .body(matchesJsonSchemaInClasspath("schemas/image-search-schema.json"));
    }
}
