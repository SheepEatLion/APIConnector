package com.auff.apiConnector.adapters.controller;

import com.auff.apiConnector.application.dto.PhotoResponse;
import com.auff.apiConnector.domain.model.Provider;
import com.auff.apiConnector.ports.inbound.GetPhotoUseCase;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "sheep-eat-lion/v1/photos")
public class PhotoController {

  private final GetPhotoUseCase getPhotoUseCase;

  @GetMapping
  public ResponseEntity<PhotoResponse> getPhoto(@RequestParam Provider provider, @RequestParam LocalDate takenDate) {
    return ResponseEntity.ok(getPhotoUseCase.excute());
  }
}
