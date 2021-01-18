package by.kes.altReality.controller;

import static java.util.Arrays.asList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

import by.kes.altReality.controller.model.GetRealityCharacteristicsResponse;
import by.kes.altReality.controller.model.PostRealityCharacteristicsRequest;
import by.kes.altReality.controller.model.PostRealityRequest;
import by.kes.altReality.data.dao.RealityCharacteristicsDao;
import by.kes.altReality.data.dao.RealityElementsDao;
import by.kes.altReality.data.domain.DateBreakdown;

@RestController
public class AltRealityController {

  @Autowired
  private RealityCharacteristicsDao realityCharacteristicsDao;

  @Autowired
  private RealityElementsDao realityElementsDao;

  @GetMapping(path = "/api/ping")
  public String ping() {
    return "Hello from AltReality";
  }

  @GetMapping(path = "/api/realities/{id}/characteristics")
  public GetRealityCharacteristicsResponse getRealityCharacteristics(@PathVariable final String id) {
    final GetRealityCharacteristicsResponse response = new GetRealityCharacteristicsResponse();
    response.setRealities(asList(realityCharacteristicsDao.get(id)));
    return response;
  }

  @GetMapping(path = "/api/realities/characteristics")
  public GetRealityCharacteristicsResponse getRealities() {
    final GetRealityCharacteristicsResponse response = new GetRealityCharacteristicsResponse();
    response.setRealities(realityCharacteristicsDao.getAll());
    return response;
  }

  @PostMapping(path = "/api/realities")
  public void addReality(@RequestBody final PostRealityRequest request) {
    realityCharacteristicsDao.saveAll(request.getRealities());
  }

  @PostMapping(path = "/api/realities/{id}/characteristics")
  public void addRealityCharacteristics(@PathVariable final String id,
                                        @RequestBody final PostRealityCharacteristicsRequest request) {
    final Collection<DateBreakdown> dateBreakdowns = request.getDateBreakdowns();
    dateBreakdowns.stream().filter(db -> db.getId() == null).forEach(db -> db.setId(id));
    realityElementsDao.saveAll(dateBreakdowns);
  }
}
