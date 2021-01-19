package by.kes.altReality.controller;

import static java.util.Arrays.asList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import by.kes.altReality.controller.model.GetRealityCharacteristicsResponse;
import by.kes.altReality.controller.model.PostGeometryRequest;
import by.kes.altReality.controller.model.PostRealityRequest;
import by.kes.altReality.controller.model.PostRealityResponse;
import by.kes.altReality.controller.model.RetrieveRealityTokensResponse;
import by.kes.altReality.controller.model.common.GenericResponse;
import by.kes.altReality.data.dao.RealityCharacteristicsDao;
import by.kes.altReality.data.dao.RealityElementsDao;
import by.kes.altReality.service.AlternativeRealityService;

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

  /**
   * Dangerous method?
   *
   * @param accessToken
   * @return
   */
  @GetMapping(path = "/api/realities")
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


  @GetMapping(path = "/api/realities/{id}")
  public GenericResponse getRealityCharacteristics(
      @PathVariable final String id,
      @RequestHeader("X-Reality-Access-Token") final String accessToken) {
    final GetRealityCharacteristicsResponse response = new GetRealityCharacteristicsResponse();
    response.setRealities(asList(realityService.retrieveAlternativeReality(id, accessToken)));
    return response;
  }

  @GetMapping(path = "/api/realities/tokens")
  public GenericResponse getRealityTokens(
      @RequestHeader(value = "X-User-Token", defaultValue = "anonymous") final String userToken) {
    return RetrieveRealityTokensResponse.builder()
        .tokens(realityService.retrieveRealityTokens(userToken))
        .build();
  }

  @PostMapping(path = "/api/test/geometry")
  public GenericResponse postGeometry(@RequestBody final PostGeometryRequest request) {
    return null;
  }
}
