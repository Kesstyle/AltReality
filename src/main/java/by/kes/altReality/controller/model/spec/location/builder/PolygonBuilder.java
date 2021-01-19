package by.kes.altReality.controller.model.spec.location.builder;

import static by.kes.altReality.controller.model.spec.location.domain.GeometryType.POLYGON;
import static by.kes.altReality.controller.model.spec.location.domain.Point.NULL_POINT;
import static org.springframework.util.CollectionUtils.isEmpty;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

import by.kes.altReality.controller.model.spec.location.domain.Geometry;
import by.kes.altReality.controller.model.spec.location.domain.Point;
import by.kes.altReality.controller.model.spec.location.domain.Polygon;

@Component
public class PolygonBuilder implements GeometryElementBuilder<Polygon, List<Point>> {

  @Override
  public Geometry build(Polygon polygon) {
    final Geometry geometry = Geometry.builder().build();
    geometry.setType(POLYGON.getName());
    final List<Point> sourcePoints = polygon.getCoordinates();
    if (isEmpty(sourcePoints)) {
      return geometry;
    }
    final List<List<Point>> destPoints = new ArrayList<>();
    int listIndex = 0;
    for (final Point point: sourcePoints) {
      if (point == NULL_POINT) {
        listIndex++;
        continue;
      }
      if (destPoints.size() == listIndex) {
        destPoints.add(new ArrayList<>());
      }
      destPoints.get(listIndex).add(point);
    }
    geometry.setCoordinates(destPoints);
    return geometry;
  }

  @Override
  public boolean isValidData(final List geometryCoordinates) {
    if (geometryCoordinates.isEmpty() || !(geometryCoordinates.get(0) instanceof List)) {
      return false;
    }
    final List points = (List) geometryCoordinates.get(0);
    if (CollectionUtils.isEmpty(points) || !(points.get(0) instanceof Point)) {
      return false;
    }

    return true;
  }

  public List<Point> buildCoordinates(final List geometryCoordinates) {
    return (List<Point>) geometryCoordinates.stream()
        .reduce((a, b) -> {
          final List<Point> combinedPoints = new ArrayList<>();
          combinedPoints.addAll((List) a);
          combinedPoints.add(NULL_POINT);
          combinedPoints.addAll((List) b);
          return combinedPoints;
        }).orElse(new ArrayList<>());
  }

  public Polygon newInstance() {
    return new Polygon();
  }
}
