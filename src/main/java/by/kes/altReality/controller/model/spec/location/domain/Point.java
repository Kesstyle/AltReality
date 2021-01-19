package by.kes.altReality.controller.model.spec.location.domain;

import static java.util.Arrays.asList;

import java.math.BigDecimal;
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
public class Point extends GeometryElement<List<BigDecimal>> {

  private BigDecimal lon;
  private BigDecimal lat;

  public List<BigDecimal> getCoordinates() {
    if (lon == null || lat == null) {
      return new ArrayList<>();
    }
    return asList(lon, lat);
  }

  public static final Point NULL_POINT = new Point(null, null);
}
