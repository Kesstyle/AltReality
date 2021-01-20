package by.kes.specification.location.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import by.kes.specification.location.domain.Geometry;
import by.kes.specification.location.domain.GeometryElement;
import by.kes.specification.location.domain.GeometryType;

@Component
public class GeometryBuilder {

  @Autowired
  private LineStringBuilder lineStringBuilder;

  @Autowired
  private MultiLineStringBuilder multiLineStringBuilder;

  @Autowired
  private PointBuilder pointBuilder;

  @Autowired
  private MultiPointBuilder multiPointBuilder;

  @Autowired
  private PolygonBuilder polygonBuilder;

  @Autowired
  private MultiPolygonBuilder multiPolygonBuilder;

  private Map<GeometryType, GeometryElementBuilder> buildersMap;

  public GeometryElement build(final Geometry geometry) {
    if (geometry == null || geometry.getType() == null
        || CollectionUtils.isEmpty(geometry.getCoordinates())) {
      return null;
    }
    return buildersMap.get(GeometryType.getByName(geometry.getType())).build(geometry);
  }

  @PostConstruct
  private void init() {
    buildersMap = new ConcurrentHashMap<>();
    buildersMap.put(GeometryType.LINE_STRING, lineStringBuilder);
    buildersMap.put(GeometryType.MULTI_LINE_STRING, multiLineStringBuilder);
    buildersMap.put(GeometryType.POLYGON, polygonBuilder);
    buildersMap.put(GeometryType.MULTI_POLYGON, multiPolygonBuilder);
    buildersMap.put(GeometryType.MULTIPOINT, multiPointBuilder);
    buildersMap.put(GeometryType.POINT, pointBuilder);
  }
}
