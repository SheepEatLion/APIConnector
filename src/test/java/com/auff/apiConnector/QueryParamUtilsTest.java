package com.auff.apiConnector;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.auff.apiConnector.adapters.client.dto.NasaRequest;
import com.auff.apiConnector.infra.util.QueryParamUtils;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.springframework.util.MultiValueMap;

class QueryParamUtilsTest {

  @Test
  void shouldReturnDefaultValue_WhenParamIsNull() {
    NasaRequest queryParams = NasaRequest.builder().build();

    MultiValueMap<String, String> queryParamsMap = QueryParamUtils.toQueryParams(queryParams);

    assertEquals("DEMO_KEY", queryParamsMap.getFirst("api_key"));
  }

  @Test
  void shouldReturnString_WhenParamIsLocalDateType() {
    NasaRequest queryParams = NasaRequest.builder().specificDate(LocalDate.now()).build();

    MultiValueMap<String, String> queryParamsMap = QueryParamUtils.toQueryParams(queryParams);

    assertEquals("DEMO_KEY", queryParamsMap.getFirst("api_key"));
    assertEquals(String.valueOf(LocalDate.now()), queryParamsMap.getFirst("date"));
  }

  @Test
  void shouldReturnString_WhenParamIsInteger() {
    NasaRequest queryParams = NasaRequest.builder().numberOfRandomImages(2).build();

    MultiValueMap<String, String> queryParamsMap = QueryParamUtils.toQueryParams(queryParams);

    assertEquals("DEMO_KEY", queryParamsMap.getFirst("api_key"));
    assertEquals("2", queryParamsMap.getFirst("count"));
  }
}
