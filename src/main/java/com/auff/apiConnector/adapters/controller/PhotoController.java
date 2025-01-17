package com.auff.apiConnector.adapters.controller;

import com.auff.apiConnector.application.dto.PhotoRequest;
import com.auff.apiConnector.application.dto.PhotoResponse;
import com.auff.apiConnector.ports.inbound.GetPhotoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "apis/v1/photos")
public class PhotoController {

  private final GetPhotoUseCase getPhotoUseCase;

  @GetMapping
  public ResponseEntity<PhotoResponse> getPhoto(PhotoRequest photoRequest) {
    return ResponseEntity.ok(getPhotoUseCase.excute(photoRequest));
  }
}
