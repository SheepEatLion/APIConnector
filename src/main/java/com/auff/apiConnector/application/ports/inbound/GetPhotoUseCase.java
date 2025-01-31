package com.auff.apiConnector.application.ports.inbound;

import com.auff.apiConnector.application.dto.PhotoQuery;
import com.auff.apiConnector.domain.model.Photo;
import org.springframework.stereotype.Component;

@Component
public interface GetPhotoUseCase {

  Photo getPhoto(PhotoQuery photoQuery);
}
