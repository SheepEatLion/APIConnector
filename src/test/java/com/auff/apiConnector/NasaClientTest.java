package com.auff.apiConnector;

import com.auff.apiConnector.adapters.client.NasaClient;
import com.auff.apiConnector.application.dto.PhotoQuery;
import com.auff.apiConnector.domain.model.Photo;
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
    Flux<Photo> response = nasaClient.loadPhoto(PhotoQuery.builder().build());

    StepVerifier.create(response)
        .expectNextMatches(photo -> photo.getLink() != null)
        .verifyComplete();
  }
}
