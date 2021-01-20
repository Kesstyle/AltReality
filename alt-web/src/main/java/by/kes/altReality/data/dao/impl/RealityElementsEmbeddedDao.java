package by.kes.altReality.data.dao.impl;

import static java.util.stream.Collectors.toList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicLong;

import by.kes.altReality.data.domain.DateBreakdown;
import by.kes.altReality.data.domain.RealityCharacteristics;
import by.kes.altReality.data.persistence.RealityDataStore;
import by.kes.altReality.data.dao.RealityElementsDao;
import by.kes.altReality.data.domain.NullReality;
import by.kes.altReality.data.utils.RealityUtils;

@Component
public class RealityElementsEmbeddedDao implements RealityElementsDao {

  @Autowired
  private RealityDataStore realityDataStore;

  @Autowired
  private NullReality nullReality;

  @Autowired
  private RealityUtils realityUtils;

  private AtomicLong indexCounter = new AtomicLong(0);

  @Override
  public boolean save(final DateBreakdown dateBreakdown) {
    final String realityId = realityUtils.getRealityIdFromDateBreakdown(dateBreakdown);
    final RealityCharacteristics realityCharacteristics = realityDataStore.get(realityId);
    if (realityCharacteristics == null) {
      return false;
    }
    dateBreakdown.setId(realityUtils.assignBreakdownId(dateBreakdown.getId(),
        String.valueOf(indexCounter.incrementAndGet())));
    realityCharacteristics.getDateBreakdowns().add(dateBreakdown);
    realityDataStore.update(realityCharacteristics);
    return true;
  }

  @Override
  public boolean saveAll(final Collection<DateBreakdown> breakdowns) {
    final Collection<String> ids = breakdowns.stream()
        .map(realityUtils::getRealityIdFromDateBreakdown).collect(toList());
    final Collection<RealityCharacteristics> realityCharacteristics = realityDataStore.getAll(ids);
    breakdowns.stream()
        .map(br -> {
          br.setId(realityUtils.assignBreakdownId(br.getId(),
              String.valueOf(indexCounter.incrementAndGet())));
          return br;
        })
        .forEach(br -> realityCharacteristics.stream()
        .filter(real -> real.getId().equalsIgnoreCase(realityUtils.getRealityIdFromDateBreakdown(br)))
        .findFirst().orElse(nullReality).getDateBreakdowns().add(br));
    realityDataStore.updateAll(realityCharacteristics);
    return true;
  }

  @Override
  public DateBreakdown get(String s) {
    return null;
  }

  @Override
  public Collection<DateBreakdown> getAll() {
    return null;
  }

  @Override
  public DateBreakdown update(DateBreakdown dateBreakdown) {
    return null;
  }

  @Override
  public boolean delete(String s) {
    return false;
  }
}
