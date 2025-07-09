package tests;
import io.restassured.response.Response;
import org.lesaka.utils.ApiClient;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class VotesNegativeTest {

    @Test(description = "POST /votes - Submit vote without image_id - should fail")
    public void testPostVoteWithoutImageId() {
        String invalidPayload = "{ \"value\": 1 }"; // Missing image_id

        Response response = ApiClient.post("/votes", invalidPayload);
        assertEquals(response.getStatusCode(), 400, "Expected 400 Bad Request for missing image_id");
    }

    @Test(description = "POST /votes - Submit vote with invalid value")
    public void testPostVoteWithInvalidValue() {
        String invalidPayload = "{ \"image_id\": \"b1\", \"value\": 999 }"; // invalid value

        Response response = ApiClient.post("/votes", invalidPayload);
        assertEquals(response.getStatusCode(), 400, "Expected 400 Bad Request for invalid vote value");
    }
}
