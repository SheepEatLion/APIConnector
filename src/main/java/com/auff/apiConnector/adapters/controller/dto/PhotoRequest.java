package com.auff.apiConnector.adapters.controller.dto;

import com.auff.apiConnector.domain.model.Provider;
import com.auff.apiConnector.infra.config.LocalDateDefaultDeserializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.Value;

@Value
public class PhotoRequest {

  @NotNull
  Provider provider;

  @JsonDeserialize(using = LocalDateDefaultDeserializer.class)
  LocalDate takenDate;

  @JsonProperty(defaultValue = "1")
  @Min(1)
  Integer count;
}
