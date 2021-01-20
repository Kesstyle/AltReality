package by.kes.specification.location.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

public enum LocationFeatureType {

  @JsonProperty("Feature") FEATURE("Feature"),
  @JsonProperty("FeatureCollection") FEATURE_COLLECTION("FeatureCollection");

  private String name;

  private LocationFeatureType(final String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public static LocationFeatureType getByName(final String name) {
    return Arrays.stream(values()).filter(v -> v.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
  }
}
