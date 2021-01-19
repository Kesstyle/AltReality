package by.kes.altReality.controller.model.spec.location.builder;

import java.util.List;

import by.kes.altReality.controller.model.spec.location.domain.Geometry;
import by.kes.altReality.controller.model.spec.location.domain.GeometryElement;

public interface GeometryElementBuilder<T extends GeometryElement, S> {

  S buildCoordinates(List geometryCoordinates);
  boolean isValidData(List geometryCoordinates);
  Geometry build(T t);
  T newInstance();

  default S validateAndBuildCoordinates(final List geometryCoordinates) {
    if (!isValidData(geometryCoordinates)) {
      return null;
    }
    return buildCoordinates(geometryCoordinates);
  }

  default T build(Geometry geometry) {
    return build(geometry.getCoordinates());
  }

  default T build(List geometryCoordinates) {
    final T t = newInstance();
    t.setCoordinates(validateAndBuildCoordinates(geometryCoordinates));
    return t;
  }

  default S buildCoordinates(Geometry geometry) {
    return buildCoordinates(geometry.getCoordinates());
  }
}
