package com.auff.apiConnector.adapters.controller.dto;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class PhotoResponse {

  String title;

  String explanation;

  String takenBy;

  String link;

  String copyright;
}
