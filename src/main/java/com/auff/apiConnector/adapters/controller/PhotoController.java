package com.auff.apiConnector.adapters.controller;

import com.auff.apiConnector.adapters.controller.dto.PhotoRequest;
import com.auff.apiConnector.adapters.controller.dto.PhotoResponse;
import com.auff.apiConnector.application.dto.PhotoQuery;
import com.auff.apiConnector.application.ports.inbound.GetPhotoUseCase;
import com.auff.apiConnector.domain.model.Photo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/v1/photos")
public class PhotoController {

  private final GetPhotoUseCase getPhotoUseCase;

  @GetMapping
  public Flux<PhotoResponse> getPhoto(@Valid PhotoRequest request) {
    Flux<Photo> result = getPhotoUseCase.getPhoto(PhotoQuery.builder()
        .takenDate(request.getTakenDate())
        .provider(request.getProvider())
        .build()
    );

    return result.map(photo -> PhotoResponse.builder()
        .title(photo.getTitle())
        .explanation(photo.getExplanation())
        .takenBy(photo.getTakenBy())
        .link(photo.getLink())
        .copyright(photo.getCopyright())
        .build());
  }
}
