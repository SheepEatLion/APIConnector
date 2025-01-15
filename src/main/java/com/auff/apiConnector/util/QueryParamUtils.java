package com.auff.apiConnector.util;

import com.auff.apiConnector.infra.util.ObjectMapperProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class QueryParamUtils {
  private static final ObjectMapper objectMapper = ObjectMapperProvider.getInstance();

  public static MultiValueMap<String, String> toQueryParams(Object object) {
    Map<String, Object> map = objectMapper.convertValue(object, Map.class);
    MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
    map.forEach((key, value) -> multiValueMap.add(key, value != null ? value.toString() : null));

    return multiValueMap;
  }
}
