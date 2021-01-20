package by.kes.specification.location.builder;

import static by.kes.specification.location.domain.GeometryType.LINE_STRING;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

import by.kes.specification.location.domain.Geometry;
import by.kes.specification.location.domain.LineString;
import by.kes.specification.location.domain.Point;

@Component
public class LineStringBuilder implements GeometryElementBuilder<LineString, List<Point>> {

  @Override
  public List<Point> buildCoordinates(List geometryCoordinates) {
    return geometryCoordinates;
  }

  @Override
  public Geometry build(LineString lineString) {
    final Geometry geometry = Geometry.builder().build();
    geometry.setType(LINE_STRING.getName());
    geometry.setCoordinates(lineString.getCoordinates());
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
  public LineString newInstance() {
    return new LineString();
  }
}
