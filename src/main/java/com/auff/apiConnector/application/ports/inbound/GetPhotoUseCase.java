package com.auff.apiConnector.application.ports.inbound;

import com.auff.apiConnector.application.dto.PhotoQuery;
import com.auff.apiConnector.domain.model.Photo;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public interface GetPhotoUseCase {

  Flux<Photo> getPhoto(PhotoQuery photoQuery);
}
