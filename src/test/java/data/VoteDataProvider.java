package data;

import org.testng.annotations.DataProvider;

public class VoteDataProvider {

    @DataProvider(name = "voteData")
    public static Object[][] provideVoteData() {
        return new Object[][] {
                { "b1", 1 },    // upvote
                { "b2", -1 }    // downvote
        };
    }
}

