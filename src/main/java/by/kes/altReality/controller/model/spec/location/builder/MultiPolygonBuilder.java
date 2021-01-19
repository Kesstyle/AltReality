package by.kes.altReality.controller.model.spec.location.builder;

import static by.kes.altReality.controller.model.spec.location.domain.GeometryType.MULTI_POLYGON;
import static org.springframework.util.CollectionUtils.isEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import by.kes.altReality.controller.model.spec.location.domain.Geometry;
import by.kes.altReality.controller.model.spec.location.domain.MultiPolygon;
import by.kes.altReality.controller.model.spec.location.domain.Point;
import by.kes.altReality.controller.model.spec.location.domain.Polygon;

@Component
public class MultiPolygonBuilder implements GeometryElementBuilder<MultiPolygon, List<Polygon>> {

  @Autowired
  private PolygonBuilder polygonBuilder;

  @Override
  public Geometry build(MultiPolygon multiPolygon) {
    final Geometry geometry = Geometry.builder().build();
    geometry.setType(MULTI_POLYGON.getName());
    geometry.setCoordinates(multiPolygon.getCoordinates().stream()
        .map(coordinates -> polygonBuilder.build(coordinates).getCoordinates())
        .collect(Collectors.toList()));
    return geometry;
  }

  @Override
  public boolean isValidData(final List geometryCoordinates) {
    if (isEmpty(geometryCoordinates) || !(geometryCoordinates.get(0) instanceof List)) {
      return false;
    }
    final List listOfPoints = (List) geometryCoordinates.get(0);
    if (isEmpty(listOfPoints) || !(listOfPoints.get(0) instanceof List)) {
      return false;
    }
    final List points = (List) listOfPoints.get(0);
    if (isEmpty(points) || (points.get(0) instanceof Point)) {
      return true;
    }

    return false;
  }

  public List<Polygon> buildCoordinates(final List geometryCoordinates) {
    return (List<Polygon>) geometryCoordinates.stream()
        .map(list -> polygonBuilder.build((List) list))
        .collect(Collectors.toList());
  }

  public MultiPolygon newInstance() {
    return new MultiPolygon();
  }
}
