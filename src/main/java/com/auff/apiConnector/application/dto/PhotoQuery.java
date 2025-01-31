package com.auff.apiConnector.application.dto;

import com.auff.apiConnector.domain.model.Provider;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class PhotoQuery {

  Provider provider;

  LocalDate takenDate;
}
