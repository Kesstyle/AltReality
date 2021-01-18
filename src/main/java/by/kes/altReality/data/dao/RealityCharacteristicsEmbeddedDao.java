package by.kes.altReality.data.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.PostConstruct;

import by.kes.altReality.data.domain.CurrentSetup;
import by.kes.altReality.data.domain.DateBreakdown;
import by.kes.altReality.data.domain.Location;
import by.kes.altReality.data.domain.RealityCharacteristics;
import by.kes.altReality.data.domain.RealityQuantum;
import by.kes.altReality.data.persistence.RealityDataStore;

@Component
public class RealityCharacteristicsEmbeddedDao implements RealityCharacteristicsDao {

  @Autowired
  private RealityDataStore realityDataStore;

  private AtomicLong indexCounter = new AtomicLong(0);

  @Override
  public boolean save(RealityCharacteristics realityCharacteristics) {
    if (realityCharacteristics == null) {
      return false;
    }
    if (realityCharacteristics.getId() != null) {
      return update(realityCharacteristics) != null;
    }
    realityCharacteristics.setId(String.valueOf(indexCounter.incrementAndGet()));
    realityDataStore.put(realityCharacteristics.getId(), realityCharacteristics);
    return true;
  }

  @Override
  public boolean saveAll(Collection<RealityCharacteristics> tList) {
    tList.stream().forEach(this::save);
    return true;
  }

  @Override
  public RealityCharacteristics get(final String id) {
    return realityDataStore.get(id);
  }

  @Override
  public Collection<RealityCharacteristics> getAll() {
    return realityDataStore.getAll();
  }

  @Override
  public RealityCharacteristics update(RealityCharacteristics realityCharacteristics) {
    realityDataStore.update(realityCharacteristics);
    return realityCharacteristics;
  }

  @Override
  public boolean delete(final String id) {
    realityDataStore.delete(id);
    return true;
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

    final Location location = Location.builder().lat(lat).lon(lon).height(height).name(name).build();
    final RealityQuantum realityQuantum = RealityQuantum.builder().location(location).moment(LocalDateTime.now()).build();
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

    realityDataStore.put(realityCharacteristics.getId(), realityCharacteristics);
  }
}
