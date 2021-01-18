package by.kes.altReality.controller.model.common;

import java.util.Collection;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse extends GenericResponse {

  private Collection<Error> errors;
}
