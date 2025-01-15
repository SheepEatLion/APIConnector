package com.auff.apiConnector.client.nasa;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import lombok.Builder;

/**
 * @param specificDate         특정날짜. 기간조회와 함께 사용할 수 없고 1장만 응답한다.
 * @param startDate            기간으로 조회할 경우, 시작날짜.
 * @param endDate              기간으로 조회할 경우, 종료날짜.
 * @param numberOfRandomImages 설정한 숫자만큼의 이미지를 랜덤으로 받고 싶을 경우.
 * @param apiKey               디폴트 키로 조회 가능.
 */

@Builder
public record NasaQueryParams(
    @JsonProperty(value = "date") LocalDate specificDate,
    @JsonProperty(value = "start_date") LocalDate startDate,
    @JsonProperty(value = "end_date") LocalDate endDate,
    @JsonProperty(value = "count") Integer numberOfRandomImages,
    @JsonProperty(value = "api_key") String apiKey) {

  public NasaQueryParams {
    if (specificDate != null && (startDate != null || endDate != null || numberOfRandomImages != null)) {
        throw new IllegalArgumentException("When SpecificDate is exist, others must be null");
    }

    if (numberOfRandomImages != null && (startDate != null || endDate != null)) {
        throw new IllegalArgumentException("When NumberOfRandomImages is exist, others must be null");
    }

    if (startDate != null && endDate == null) {
        throw new IllegalArgumentException("When StartDate is exist, EndDate cannot be null or blank");
    }

    if (endDate != null && startDate == null) {
        throw new IllegalArgumentException("When EndDate is exist, StartDate cannot be null or blank");
    }
  }

  @Override
  public String apiKey() {
    return apiKey == null ? "DEMO_KEY" : apiKey;
  }
}
