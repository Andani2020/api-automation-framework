package tests;
import org.lesaka.utils.ConfigManager;
import io.qameta.allure.*;
import io.restassured.response.Response;
import data.BreedDataProvider;
import org.lesaka.utils.ApiClient;
import org.testng.annotations.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.testng.Assert.assertEquals;

@Epic("Cat API")
@Feature("Breeds Endpoint")
public class BreedTest {

    @Story("GET /breeds validation")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test GET /breeds using a data provider with schema validation for valid responses")
    @Test(dataProvider = "breedEndpoints", dataProviderClass = BreedDataProvider.class)
    public void testGetBreeds(String path, int expectedStatus) {
        String resolvedPath = resolvePath(path);

        logRequestPath(resolvedPath);

        Response response = ApiClient.get(resolvedPath);
        int actualStatus = response.getStatusCode();

        logResponseStatus(actualStatus);

        assertEquals(actualStatus, expectedStatus, "Unexpected HTTP status");

        // Only validate schema if response is 200 OK
        if (expectedStatus == 200) {
            validateSchema(response);
        }
    }

    @Step("Resolving API path from input: {path}")
    private String resolvePath(String path) {
        return path.startsWith("/") ? path : ConfigManager.get(path);
    }

    @Step("Request sent to path: {path}")
    private void logRequestPath(String path) {
        // This method is for logging in Allure
        Allure.step("Request sent to path: " + path);
        System.out.println("Request sent to path: " + path);
    }

    @Step("Response status received: {status}")
    private void logResponseStatus(int status) {
        // This method is for Allure step trace
        Allure.step("Response status received: " + status);
        System.out.println("Response status received: " + status);
    }

    @Step("Validating response against JSON schema: breeds-schema.json")
    private void validateSchema(Response response) {
        response.then().assertThat()
                .body(matchesJsonSchemaInClasspath("schemas/breeds-schema.json"));
    }
}
