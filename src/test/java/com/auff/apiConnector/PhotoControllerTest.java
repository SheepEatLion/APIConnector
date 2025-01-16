package com.auff.apiConnector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import com.auff.apiConnector.adapters.controller.PhotoController;
import com.auff.apiConnector.application.dto.PhotoResponse;
import com.auff.apiConnector.ports.inbound.GetPhotoUseCase;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest(PhotoController.class)
class PhotoControllerTest {

  @Autowired
  private WebTestClient mockMvc;

  @MockitoBean
  private GetPhotoUseCase getPhotoUseCase;

  private PhotoResponse photoResponse;

  @BeforeEach
  void responseSetUp() {
    photoResponse = PhotoResponse.builder()
        .title("my_earth_my_youth")
        .explanation("bravo. my life.")
        .takenBy("cole")
        .link("https://testcode.photo.com/1234.jpg")
        .copyright("own by this test.")
        .build();
  }

  @Test
  void testGetPhoto() {
    when(getPhotoUseCase.excute()).thenReturn(photoResponse);

    mockMvc.get().uri(
        uriBuilder -> uriBuilder
            .path("/sheep-eat-lion/v1/photos")
            .queryParam("provider", "NASA")
            .queryParam("takenDate", LocalDate.now().toString())
            .build())
        .exchange()
        .expectStatus().isOk()
        .expectBody(PhotoResponse.class)
        .value(response -> {
          assertEquals("my_earth_my_youth", response.title());
          assertNotNull(response.link());
        });
  }
}
