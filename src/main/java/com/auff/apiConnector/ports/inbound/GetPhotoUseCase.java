package com.auff.apiConnector.ports.inbound;

import com.auff.apiConnector.application.dto.PhotoResponse;
import org.springframework.stereotype.Component;

@Component
public interface GetPhotoUseCase {

  PhotoResponse excute();
}
