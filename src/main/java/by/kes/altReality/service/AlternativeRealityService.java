package by.kes.altReality.service;

import static by.kes.altReality.data.security.AccessRight.ADMIN;
import static by.kes.altReality.data.security.AccessRight.OWNER;
import static by.kes.altReality.data.security.AccessRight.USER;
import static java.util.Arrays.asList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import by.kes.altReality.controller.model.spec.location.FeaturedLocation;
import by.kes.altReality.controller.model.spec.location.LocationFeatureType;
import by.kes.altReality.controller.model.spec.location.domain.Geometry;
import by.kes.altReality.controller.model.spec.location.domain.GeometryType;
import by.kes.altReality.controller.utils.JwtTokenUtil;
import by.kes.altReality.data.dao.RealityCharacteristicsDao;
import by.kes.altReality.data.dao.RealityElementsDao;
import by.kes.altReality.data.dao.impl.AccessEmbeddedDao;
import by.kes.altReality.data.domain.CurrentSetup;
import by.kes.altReality.data.domain.DateBreakdown;
import by.kes.altReality.data.domain.RealityCharacteristics;
import by.kes.altReality.data.domain.RealityQuantum;
import by.kes.altReality.data.security.AccessRight;
import by.kes.altReality.data.security.AccessToken;
import by.kes.altReality.data.utils.SecurityUtils;

@Service
public class AlternativeRealityService {

  @Autowired
  private RealityCharacteristicsDao realityCharacteristicsDao;

  @Autowired
  private AccessEmbeddedDao accessDao;

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  @Autowired
  private SecurityUtils securityUtils;

  @Autowired
  private RealityElementsDao realityElementsDao;

  public RealityCharacteristics retrieveAlternativeReality(final String realityId, final String realityToken) {
    securityUtils.assertRealityRights("You don't have rights to view this reality: " + realityId,
        realityToken, realityId);
    return realityCharacteristicsDao.get(realityId);
  }

  public Collection<RealityCharacteristics> retrieveAllRealities(final String userToken) {
    securityUtils.assertUserRights("You don't have rights for this operation!", userToken, ADMIN);
    return realityCharacteristicsDao.getAll();
  }

  public Map<String, List<AccessToken>> retrieveRealityTokens(final String userToken) {
    securityUtils.assertUserRights("You don't have rights for this operation!", userToken, ADMIN);
    return accessDao.getRealityTokens();
  }

  public String saveAlternativeReality(final RealityCharacteristics realityCharacteristics, final String userToken) {
    securityUtils.assertUserRights("You don't have rights for this operation!", userToken, USER);
    final boolean saved = realityCharacteristicsDao.save(realityCharacteristics);
    if (saved) {
      final String jwtToken = jwtTokenUtil.generateToken(realityCharacteristics);
      accessDao.saveRealityToken(realityCharacteristics.getId(),
          AccessToken.builder()
              .token(jwtToken)
              .accessRight(OWNER)
              .realityId(realityCharacteristics.getId())
              .build());
      return jwtToken;
    }
    return null;
  }

  public void addDateBreakdowns(final Collection<DateBreakdown> dateBreakdowns, final String realityId,
                                  final String realityToken) {
    securityUtils.assertRealityRights("You don't have rights to edit this reality: " + realityId,
        realityToken, realityId, OWNER);
    dateBreakdowns.stream().filter(db -> db.getId() == null).forEach(db -> db.setId(realityId));
    realityElementsDao.saveAll(dateBreakdowns);
  }

  @PostConstruct
  private void prepareTestData() {
    final BigDecimal lon = BigDecimal.valueOf(56.11);
    final BigDecimal lat = BigDecimal.valueOf(14.15);
    final BigDecimal height = BigDecimal.valueOf(11);
    final String name = "Random";
    final String setupItem = "Formed program";
    final CurrentSetup currentSetup = new CurrentSetup();
    final List<String> currentSetups = new ArrayList<>();
    currentSetups.add(setupItem);
    currentSetup.setEvents(currentSetups);

    final Geometry geometry = Geometry.builder()
        .type(GeometryType.POINT.getName())
        .coordinates(asList(BigDecimal.valueOf(123.11), BigDecimal.valueOf(55.22)))
        .build();
    final FeaturedLocation location = FeaturedLocation.builder()
        .geometry(geometry)
        .type(LocationFeatureType.FEATURE)
        .properties(new HashMap<>() {{ put("situation", "critical"); }})
        .build();
    final RealityQuantum realityQuantum = RealityQuantum.builder()
        .location(location).moment(LocalDateTime.now()).build();
    final RealityCharacteristics realityCharacteristics = new RealityCharacteristics();
    realityCharacteristics.setId("0");
    final DateBreakdown dateBreakdown = DateBreakdown.builder()
        .realityQuantum(realityQuantum)
        .currentSetup(currentSetup)
        .build();
    final List<DateBreakdown> dateBreakdowns = new ArrayList<>();
    dateBreakdowns.add(dateBreakdown);
    realityCharacteristics.setDateBreakdowns(dateBreakdowns);
    realityCharacteristics.setRealityName("Real World");

    accessDao.saveUserToken(AccessToken.builder()
        .accessRight(ADMIN)
        .token("admin")
        .build());
    accessDao.saveUserToken(AccessToken.builder()
        .accessRight(USER)
        .token("user")
        .build());
    accessDao.saveUserToken(AccessToken.builder()
        .accessRight(AccessRight.GUEST)
        .token("guest")
        .build());
    saveAlternativeReality(realityCharacteristics, "user");

  }
}
