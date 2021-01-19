package by.kes.altReality.controller.model.spec.location;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Collection;
import java.util.Map;

import by.kes.altReality.controller.model.spec.location.domain.Geometry;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(NON_NULL)
public class FeaturedLocation extends Location {

  private Geometry geometry;
  private Map<String, String> properties;
  private Collection<FeaturedLocation> locations;
  private LocationFeatureType type;
}
