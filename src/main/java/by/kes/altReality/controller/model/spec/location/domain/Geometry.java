package by.kes.altReality.controller.model.spec.location.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class Geometry {

  private String type;
  private List coordinates;

  public GeometryType geometryType() {
    return GeometryType.getByName(type);
  }

  public List getCoordinates() {
    if (coordinates == null) {
      coordinates = new ArrayList();
    }
    return coordinates;
  }
}
