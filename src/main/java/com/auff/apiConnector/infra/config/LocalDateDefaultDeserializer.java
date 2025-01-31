package com.auff.apiConnector.infra.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.time.LocalDate;

public class LocalDateDefaultDeserializer extends JsonDeserializer<LocalDate> {

  @Override
  public LocalDate deserialize(JsonParser parser, DeserializationContext context) throws IOException {
    String date = parser.getText();
    if (date == null || date.isEmpty()) {
      return LocalDate.now();
    }
    return LocalDate.parse(date);
  }
}
