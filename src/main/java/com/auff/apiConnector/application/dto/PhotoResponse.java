package com.auff.apiConnector.application.dto;

import lombok.Builder;

@Builder
public record PhotoResponse(
    String title,
    String explanation,
    String takenBy,
    String link,
    String copyright
) {

}
