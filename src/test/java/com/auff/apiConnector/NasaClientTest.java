package com.auff.apiConnector;

import com.auff.apiConnector.adapters.client.NasaClient;
import com.auff.apiConnector.adapters.client.NasaQueryParams;
import com.auff.apiConnector.adapters.client.NasaResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@SpringBootTest
class NasaClientTest {

  @Autowired
  NasaClient nasaClient;

  @Test
  void shouldReturnCorrectUrl_WhenNasaApiRequest() {
    Flux<NasaResponse> response = nasaClient.getPlanetaryImageBy(NasaQueryParams.builder().build());

    StepVerifier.create(response)
        .expectNextMatches(nasaResponse -> nasaResponse.imageUrl() != null)
        .verifyComplete();
  }
}
