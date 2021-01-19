package by.kes.altReality.controller.model.spec.location.builder;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;

import by.kes.altReality.controller.model.spec.location.domain.Geometry;
import by.kes.altReality.controller.model.spec.location.domain.GeometryType;
import by.kes.altReality.controller.model.spec.location.domain.Point;

@Component
public class PointBuilder implements GeometryElementBuilder<Point, List<BigDecimal>> {

  @Override
  public List<BigDecimal> buildCoordinates(List geometryCoordinates) {
    return geometryCoordinates;
  }

  @Override
  public boolean isValidData(List geometryCoordinates) {
    if (CollectionUtils.isEmpty(geometryCoordinates) || !(geometryCoordinates.get(0) instanceof BigDecimal)) {
      return false;
    }
    return true;
  }

  @Override
  public Geometry build(Point point) {
    final Geometry geometry = Geometry.builder().build();
    geometry.setType(GeometryType.POINT.getName());
    geometry.setCoordinates(point.getCoordinates());
    return geometry;
  }

  @Override
  public Point newInstance() {
    return new Point();
  }
}
