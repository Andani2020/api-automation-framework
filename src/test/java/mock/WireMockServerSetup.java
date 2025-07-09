package mock;

import com.github.tomakehurst.wiremock.WireMockServer;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class WireMockServerSetup {

    public static WireMockServer startMockServer() {
        WireMockServer wireMockServer = new WireMockServer(8089); // default port
        wireMockServer.start();

        configureFor("localhost", 8089);

        // POST /votes
        wireMockServer.stubFor(post(urlEqualTo("/votes"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"id\":123456,\"message\":\"Vote successfully created\"}")));

        // GET /votes
        wireMockServer.stubFor(get(urlEqualTo("/votes"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("[{\"id\":123456,\"image_id\":\"b1\",\"sub_id\":\"user-001\",\"value\":1}]")));

        return wireMockServer;
    }
}

