package by.kes.altReality.controller.advice;

import static java.util.Arrays.asList;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import by.kes.altReality.controller.model.common.ErrorResponse;
import by.kes.altReality.controller.model.common.Error;
import by.kes.altReality.controller.model.common.ErrorCode;
import by.kes.altReality.service.exception.RealityActionNotAllowedException;

@ControllerAdvice
public class AltRealityControllerAdvice {

  @ExceptionHandler(RealityActionNotAllowedException.class)
  @ResponseStatus(HttpStatus.FORBIDDEN)
  ErrorResponse handleException(final RealityActionNotAllowedException exception) {
    final Error error = Error.builder().code(ErrorCode.NOT_ALLOWED).message(exception.getMessage()).build();
    return ErrorResponse.builder().errors(asList(error)).build();
  }
}
