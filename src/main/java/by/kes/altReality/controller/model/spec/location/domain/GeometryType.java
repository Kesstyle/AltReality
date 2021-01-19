package by.kes.altReality.controller.model.spec.location.domain;

import java.util.Arrays;

public enum GeometryType {

  POINT("Point"), LINE_STRING("LineString"), POLYGON("Polygon"), MULTIPOINT("MultiPoint"),
  MULTI_LINE_STRING("MultiLineString"), MULTI_POLYGON("MultiPolygon");

  private String name;

  private GeometryType(final String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public static GeometryType getByName(final String name) {
    return Arrays.stream(values()).filter(v -> v.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
  }
}
