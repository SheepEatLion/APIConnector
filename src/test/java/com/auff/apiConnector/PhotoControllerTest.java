package com.auff.apiConnector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import com.auff.apiConnector.adapters.controller.PhotoController;
import com.auff.apiConnector.adapters.controller.dto.PhotoResponse;
import com.auff.apiConnector.application.dto.PhotoQuery;
import com.auff.apiConnector.application.ports.inbound.GetPhotoUseCase;
import com.auff.apiConnector.domain.model.Photo;
import com.auff.apiConnector.domain.model.Provider;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

@WebFluxTest(PhotoController.class)
class PhotoControllerTest {

  @Autowired
  private WebTestClient mockMvc;

  @MockitoBean
  private GetPhotoUseCase getPhotoUseCase;

  private Flux<Photo> photo;

  private PhotoQuery photoQuery;

  @BeforeEach
  void mockDateSetUp() {
    photo = Flux.just(Photo.builder()
        .title("my_earth_my_youth")
        .provider(Provider.NASA)
        .explanation("bravo. my life.")
        .takenDate(LocalDate.now())
        .takenBy("cole")
        .link("https://testcode.photo.com/1234.jpg")
        .copyright("own by this test.")
        .build());

    photoQuery = PhotoQuery.builder()
        .provider(Provider.NASA)
        .takenDate(LocalDate.now())
        .build();
  }

  @Test
  void testGetPhoto() {
    when(getPhotoUseCase.getPhoto(photoQuery)).thenReturn(photo);

    mockMvc.get().uri(
        uriBuilder -> uriBuilder
            .path("/api/v1/photos")
            .queryParam("provider", "NASA")
            .queryParam("takenDate", LocalDate.now().toString())
            .build())
        .exchange()
        .expectStatus().isOk()
        .expectBodyList(PhotoResponse.class)
        .value(response -> {
          assertEquals("my_earth_my_youth", response.getFirst().getTitle());
          assertNotNull(response.getFirst().getLink());
        });
  }
}
