package by.kes.altReality.data.dao;

import java.util.Collection;

public interface GenericDao<T, ID> {

  boolean save(T t);
  boolean saveAll(Collection<T> tList);
  T get(ID id);
  Collection<T> getAll();
  T update(T t);
  boolean delete(ID id);
}
