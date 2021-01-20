package by.kes.altReality.data.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicLong;

import by.kes.altReality.data.dao.RealityCharacteristicsDao;
import by.kes.altReality.data.domain.RealityCharacteristics;
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
}
