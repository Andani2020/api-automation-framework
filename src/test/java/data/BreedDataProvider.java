package data;


import org.testng.annotations.DataProvider;

public class BreedDataProvider {

    @DataProvider(name = "breedEndpoints")
    public static Object[][] provideEndpoints() {
        return new Object[][] {
                { "breeds.endpoint", 200 },     // from config
                { "/invalidBreeds", 404 }       // hardcoded invalid
        };
    }
}
