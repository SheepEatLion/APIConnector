package com.auff.apiConnector.application.ports.outbound;

import com.auff.apiConnector.application.dto.PhotoQuery;
import com.auff.apiConnector.domain.model.Photo;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public interface LoadPhotoPort {

  Flux<Photo> loadPhoto(PhotoQuery query);
}
