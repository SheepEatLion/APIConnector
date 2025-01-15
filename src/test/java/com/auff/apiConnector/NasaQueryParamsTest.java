package com.auff.apiConnector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.auff.apiConnector.adapters.client.NasaQueryParams;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class NasaQueryParamsTest {

  @Test
  void 특정날짜_필드가_존재할때_다른필드도_존재한다면_예외를던진다() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
      NasaQueryParams.builder()
          .specificDate(LocalDate.of(2025,1,10))
          .startDate(LocalDate.of(2025, 1, 1))
          .endDate(LocalDate.now())
          .numberOfRandomImages(5)
          .build();
    });

    assertEquals("When SpecificDate is exist, others must be null", exception.getMessage());
  }

  @Test
  void 이미지개수_필드가_존재할때_다른필드도_존재한다면_예외를던진다() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
      NasaQueryParams.builder()
          .startDate(LocalDate.of(2025, 1, 1))
          .endDate(LocalDate.now())
          .numberOfRandomImages(5)
          .build();
    });

    assertEquals("When NumberOfRandomImages is exist, others must be null", exception.getMessage());
  }

  @Test
  void 시작기간이_존재할때_종료기간이_없다면_예외를던진다() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
      NasaQueryParams.builder()
          .startDate(LocalDate.of(2025, 1, 1))
          .build();
    });

    assertEquals("When StartDate is exist, EndDate cannot be null or blank", exception.getMessage());
  }

  @Test
  void 종료기간이_존재할때_시작기간이_없다면_예외를던진다() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
      NasaQueryParams.builder()
          .endDate(LocalDate.of(2025, 1, 3))
          .build();
    });

    assertEquals("When EndDate is exist, StartDate cannot be null or blank", exception.getMessage());
  }
}
