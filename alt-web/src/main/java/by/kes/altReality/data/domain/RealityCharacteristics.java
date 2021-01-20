package by.kes.altReality.data.domain;

import java.util.ArrayList;
import java.util.Collection;

import lombok.Data;

@Data
public class RealityCharacteristics extends TrackableObject<String> {

  private String realityName;
  private Collection<DateBreakdown> dateBreakdowns = new ArrayList<>();
}
