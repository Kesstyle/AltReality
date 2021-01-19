package by.kes.altReality.controller.model.spec.location.builder;

import static by.kes.altReality.controller.model.spec.location.domain.GeometryType.MULTIPOINT;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

import by.kes.altReality.controller.model.spec.location.domain.Geometry;
import by.kes.altReality.controller.model.spec.location.domain.MultiPoint;
import by.kes.altReality.controller.model.spec.location.domain.Point;

@Component
public class MultiPointBuilder implements GeometryElementBuilder<MultiPoint, List<Point>> {

  @Override
  public List<Point> buildCoordinates(List geometryCoordinates) {
    return geometryCoordinates;
  }

  @Override
  public Geometry build(MultiPoint multiPoint) {
    final Geometry geometry = Geometry.builder().build();
    geometry.setType(MULTIPOINT.getName());
    geometry.setCoordinates(multiPoint.getCoordinates());
    return geometry;
  }

  @Override
  public boolean isValidData(List geometryCoordinates) {
    if (CollectionUtils.isEmpty(geometryCoordinates)
        || !(geometryCoordinates instanceof List)
        || !(geometryCoordinates.get(0) instanceof Point)) {
      return false;
    }

    return true;
  }

  @Override
  public MultiPoint newInstance() {
    return new MultiPoint();
  }
}
