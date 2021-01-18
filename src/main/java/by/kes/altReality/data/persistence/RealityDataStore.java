package by.kes.altReality.data.persistence;

import static java.util.stream.Collectors.toList;

import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import by.kes.altReality.data.domain.RealityCharacteristics;

@Component
public class RealityDataStore {

  private final Map<String, RealityCharacteristics> data = new ConcurrentHashMap<>();
  private final Map<String, String> tokens = new ConcurrentHashMap<>();

  public RealityCharacteristics get(final String id) {
    return data.get(id);
  }

  public Collection<RealityCharacteristics> getAll(final Collection<String> ids) {
    return ids.stream().map(this::get).collect(toList());
  }

  public Collection<RealityCharacteristics> getAll() {
    return data.values();
  }

  public void put(final String id, final RealityCharacteristics realityCharacteristics) {
    synchronized (data) {
      data.put(id, realityCharacteristics);
    }
  }

  public void update(final RealityCharacteristics realityCharacteristics) {
    synchronized (data) {
      data.remove(realityCharacteristics.getId());
      data.put(realityCharacteristics.getId(), realityCharacteristics);
    }
  }

  public void updateAll(final Collection<RealityCharacteristics> realityCharacteristics) {
    realityCharacteristics.stream().forEach(this::update);
  }

  public void delete(final String id) {
    data.remove(id);
  }

  public void deleteAll(final Collection<String> ids) {
    ids.stream().forEach(this::delete);
  }
}
