package com.auff.apiConnector.application;

import com.auff.apiConnector.application.dto.PhotoQuery;
import com.auff.apiConnector.application.ports.inbound.GetPhotoUseCase;
import com.auff.apiConnector.application.ports.outbound.LoadPhotoPort;
import com.auff.apiConnector.domain.model.Photo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class GetPhotoService implements GetPhotoUseCase {

  private LoadPhotoPort loadPhotoPort;

  @Override
  public Flux<Photo> getPhoto(PhotoQuery photoQuery) {
    return loadPhotoPort.loadPhoto(photoQuery).map(item ->
      Photo.builder()
          .title(item.getTitle())
          .explanation(item.getExplanation())
          .link(item.getLink())
          .copyright(item.getCopyright())
          .build()
    );
  }
}
