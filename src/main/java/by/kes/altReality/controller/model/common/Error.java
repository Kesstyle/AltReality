package by.kes.altReality.controller.model.common;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Error {

  private ErrorCode code;
  private String message;
}
