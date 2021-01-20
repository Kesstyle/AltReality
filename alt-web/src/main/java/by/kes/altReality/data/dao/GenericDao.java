package by.kes.altReality.data.dao;

import java.util.Collection;
import java.util.stream.Collectors;

public interface GenericDao<T, ID> {

  boolean save(T t);
  T get(ID id);
  T update(T t);
  boolean delete(ID id);
  Collection<T> getAll();

  default boolean saveAll(Collection<T> tList) {
    tList.stream().forEach(this::save);
    return true;
  }

  default Collection<T> getAll(Collection<ID> ids) {
    return ids.stream().map(this::get).collect(Collectors.toList());
  }
}
