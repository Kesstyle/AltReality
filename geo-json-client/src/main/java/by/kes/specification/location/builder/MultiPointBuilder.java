package by.kes.specification.location.builder;

import static by.kes.specification.location.domain.GeometryType.MULTIPOINT;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

import by.kes.specification.location.domain.Geometry;
import by.kes.specification.location.domain.MultiPoint;
import by.kes.specification.location.domain.Point;

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
