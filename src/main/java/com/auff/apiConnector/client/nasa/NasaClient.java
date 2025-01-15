package com.auff.apiConnector.client.nasa;

import com.auff.apiConnector.util.QueryParamUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class NasaClient {

  private final WebClient toNasa;

  public Flux<NasaResponse> getPlanetaryImageBy(NasaQueryParams nasaQueryParams) {

    return toNasa.get().uri(uriBuilder -> uriBuilder.path("/planetary/apod")
            .queryParams(QueryParamUtils.toQueryParams(nasaQueryParams)).build())
        .retrieve().bodyToFlux(NasaResponse.class);
  }
}
