package by.kes.altReality.controller.model.spec.location.builder;

import static by.kes.altReality.controller.model.spec.location.domain.GeometryType.MULTI_LINE_STRING;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

import by.kes.altReality.controller.model.spec.location.domain.Geometry;
import by.kes.altReality.controller.model.spec.location.domain.LineString;
import by.kes.altReality.controller.model.spec.location.domain.MultiLineString;
import by.kes.altReality.controller.model.spec.location.domain.Point;

@Component
public class MultiLineStringBuilder implements GeometryElementBuilder<MultiLineString, List<LineString>> {

  @Autowired
  private LineStringBuilder lineStringBuilder;

  @Override
  public Geometry build(MultiLineString multiLineString) {
    final Geometry geometry = Geometry.builder().build();
    geometry.setType(MULTI_LINE_STRING.getName());
    geometry.setCoordinates(multiLineString.getCoordinates().stream()
            .map(coordinates -> lineStringBuilder.build(coordinates).getCoordinates())
            .collect(Collectors.toList()));
    return geometry;
  }

  @Override
  public boolean isValidData(final List geometryCoordinates) {
    if (CollectionUtils.isEmpty(geometryCoordinates) || !(geometryCoordinates.get(0) instanceof List)) {
      return false;
    }
    final List points = (List) geometryCoordinates.get(0);
    if (points.get(0) instanceof Point) {
      return true;
    }

    return false;
  }

  public List<LineString> buildCoordinates(final List geometryCoordinates) {
    return (List<LineString>) geometryCoordinates.stream()
        .map(list -> lineStringBuilder.build((List) list))
        .collect(Collectors.toList());
  }

  public MultiLineString newInstance() {
    return new MultiLineString();
  }
}
