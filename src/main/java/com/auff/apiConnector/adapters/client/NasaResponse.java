package com.auff.apiConnector.adapters.client;

import com.fasterxml.jackson.annotation.JsonProperty;

public record NasaResponse(

    String copyright,
    @JsonProperty(value = "date") String photoTakenDate,
    String explanation,
    @JsonProperty(value = "hdurl") String hdImageUrl,
    @JsonProperty(value = "media_type") String mediaType,
    @JsonProperty(value = "service_version") String versionOfApiService,
    @JsonProperty(value = "title") String titleOfPhoto,
    @JsonProperty(value = "url") String imageUrl) {

}
