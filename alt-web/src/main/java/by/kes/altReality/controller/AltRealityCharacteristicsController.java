package by.kes.altReality.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import by.kes.altReality.controller.model.PostRealityCharacteristicsRequest;
import by.kes.altReality.service.AlternativeRealityService;

@RestController
public class AltRealityCharacteristicsController {

  @Autowired
  private AlternativeRealityService realityService;

  @PostMapping(path = "/api/realities/{id}/events")
  public void addRealityCharacteristics(@PathVariable final String id,
                                        @RequestBody final PostRealityCharacteristicsRequest request,
                                        @RequestHeader("X-Reality-Access-Token") final String accessToken) {
    realityService.addDateBreakdowns(request.getDateBreakdowns(), id, accessToken);
  }
}
