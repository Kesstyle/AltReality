package by.kes.specification.location.domain;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Collection;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(NON_NULL)
public class FeaturedLocation extends Location {

  private Geometry geometry;
  private Map<String, String> properties;
  private Collection<FeaturedLocation> locations;
  private LocationFeatureType type;
}
