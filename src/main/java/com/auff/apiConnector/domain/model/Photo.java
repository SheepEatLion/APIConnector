package com.auff.apiConnector.domain.model;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class Photo {

  String title;

  Provider provider;

  String explanation;

  LocalDate takenDate;

  String takenBy;

  String link;

  String copyright;
}
