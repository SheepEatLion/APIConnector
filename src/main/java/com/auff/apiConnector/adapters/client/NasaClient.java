package com.auff.apiConnector.adapters.client;

import com.auff.apiConnector.adapters.client.dto.NasaRequest;
import com.auff.apiConnector.adapters.client.dto.NasaResponse;
import com.auff.apiConnector.application.dto.PhotoQuery;
import com.auff.apiConnector.application.ports.outbound.LoadPhotoPort;
import com.auff.apiConnector.domain.model.Photo;
import com.auff.apiConnector.domain.model.Provider;
import com.auff.apiConnector.infra.util.QueryParamUtils;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class NasaClient implements LoadPhotoPort {

  private final WebClient toNasa;

  @Override
  public  Flux<Photo> loadPhoto(PhotoQuery query) {
    NasaRequest request = NasaRequest.builder()
        .specificDate(query.getTakenDate())
        .startDate(null)
        .endDate(null)
        .numberOfRandomImages(null)
        .build();

    Flux<NasaResponse> response = getPlanetaryImageBy(request);

    return response.map(res -> Photo.builder()
        .title(res.titleOfPhoto())
        .provider(Provider.NASA)
        .explanation(res.explanation())
        .takenDate(LocalDate.parse(res.photoTakenDate()))
        .takenBy("Empty")
        .link(res.hdImageUrl())
        .copyright(res.copyright()).build());
  }

  private Flux<NasaResponse> getPlanetaryImageBy(NasaRequest nasaRequest) {

    return toNasa.get()
        .uri(uriBuilder -> uriBuilder.path("/planetary/apod")
            .queryParams(QueryParamUtils.toQueryParams(nasaRequest)).build())
        .retrieve()
        .bodyToFlux(NasaResponse.class);
  }
}
