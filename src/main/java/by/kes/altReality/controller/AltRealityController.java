package by.kes.altReality.controller;

import static java.util.Arrays.asList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import by.kes.altReality.controller.model.GetRealityCharacteristicsResponse;
import by.kes.altReality.controller.model.PostRealityCharacteristicsRequest;
import by.kes.altReality.controller.model.PostRealityRequest;
import by.kes.altReality.controller.model.PostRealityResponse;
import by.kes.altReality.controller.model.common.Error;
import by.kes.altReality.controller.model.common.ErrorCode;
import by.kes.altReality.controller.model.common.ErrorResponse;
import by.kes.altReality.controller.model.common.GenericResponse;
import by.kes.altReality.data.dao.RealityCharacteristicsDao;
import by.kes.altReality.data.dao.RealityElementsDao;
import by.kes.altReality.service.AlternativeRealityService;
import by.kes.altReality.service.exception.RealityActionNotAllowedException;

@RestController
public class AltRealityController {

  @Autowired
  private RealityCharacteristicsDao realityCharacteristicsDao;

  @Autowired
  private RealityElementsDao realityElementsDao;

  @Autowired
  private AlternativeRealityService realityService;

  @GetMapping(path = "/api/ping")
  public String ping() {
    return "Hello from AltReality";
  }

  @GetMapping(path = "/api/realities/{id}/characteristics")
  public GenericResponse getRealityCharacteristics(
      @PathVariable final String id,
      @RequestHeader("X-Reality-Access-Token") final String accessToken) {
    final GetRealityCharacteristicsResponse response = new GetRealityCharacteristicsResponse();
    response.setRealities(asList(realityService.retrieveAlternativeReality(id, accessToken)));
    return response;
  }

  /**
   * Dangerous method?
   *
   * @param accessToken
   * @return
   */
  @GetMapping(path = "/api/realities/characteristics")
  public GetRealityCharacteristicsResponse getRealities(
      @RequestHeader("X-Reality-Access-Token") final String accessToken) {
    final GetRealityCharacteristicsResponse response = new GetRealityCharacteristicsResponse();
    response.setRealities(realityService.retrieveAllRealities(accessToken));
    return response;
  }

  @PostMapping(path = "/api/realities")
  public PostRealityResponse addReality(
      @RequestBody final PostRealityRequest request,
      @RequestHeader(value = "X-User-Token", defaultValue = "anonymous") final String userToken) {
    final String token = realityService.saveAlternativeReality(request.getReality(), userToken);
    return PostRealityResponse.builder().accessToken(token).build();
  }

  @PostMapping(path = "/api/realities/{id}/characteristics")
  public void addRealityCharacteristics(@PathVariable final String id,
                                        @RequestBody final PostRealityCharacteristicsRequest request,
                                        @RequestHeader("X-Reality-Access-Token") final String accessToken) {
    realityService.addDateBreakdowns(request.getDateBreakdowns(), id, accessToken);
  }

  @ExceptionHandler(RealityActionNotAllowedException.class)
  @ResponseStatus(HttpStatus.FORBIDDEN)
  ErrorResponse handleException(final RealityActionNotAllowedException exception) {
    final Error error = Error.builder().code(ErrorCode.NOT_ALLOWED).message(exception.getMessage()).build();
    return ErrorResponse.builder().errors(asList(error)).build();
  }
}
