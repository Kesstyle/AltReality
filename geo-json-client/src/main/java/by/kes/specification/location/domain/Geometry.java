package by.kes.specification.location.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
