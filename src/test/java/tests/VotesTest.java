package tests;

import io.qameta.allure.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import data.VoteDataProvider;
import org.lesaka.utils.ApiClient;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Epic("Cat API Tests")
@Feature("Vote Functionality")
public class VotesTest {

    private static int voteId = -1;

    @Severity(SeverityLevel.CRITICAL)
    @Story("Submit Vote")
    @Description("Casts a vote on a cat image using POST /votes with data-driven approach")
    @Test(priority = 1, dataProvider = "voteData", dataProviderClass = VoteDataProvider.class,
            description = "POST /votes - Cast a vote for a cat image using data provider")
    public void testPostVote(String imageId, int value) {
        Allure.step("Preparing payload for vote submission");
        String jsonPayload = String.format("{ \"image_id\": \"%s\", \"value\": %d }", imageId, value);

        Allure.step("Sending POST /votes request");
        Response response = ApiClient.post("/votes", jsonPayload);

        Allure.step("Validating response status code");
        Assert.assertEquals(response.getStatusCode(), 200, "Expected 200 OK on vote submission");

        JsonPath jsonPath = response.jsonPath();
        voteId = jsonPath.getInt("id");

        Allure.step("Validating vote ID");
        Assert.assertTrue(voteId > 0, "Vote ID should be a positive integer");
    }

    @Severity(SeverityLevel.NORMAL)
    @Story("Retrieve Votes")
    @Description("Retrieves votes via GET /votes and validates submitted vote exists")
    @Test(priority = 2, dependsOnMethods = "testPostVote",
            description = "GET /votes - Ensure submitted vote exists")
    public void testGetVotes() {
        Allure.step("Sending GET /votes request");
        Response response = ApiClient.get("/votes");

        Allure.step("Asserting response status code");
        Assert.assertEquals(response.getStatusCode(), 200, "Expected 200 OK on retrieving votes");

        Allure.step("Checking if submitted vote ID exists in response");
        var voteIds = response.jsonPath().getList("id");
        assertThat("Submitted vote ID should be present in response", voteIds, hasItem(voteId));
    }
}