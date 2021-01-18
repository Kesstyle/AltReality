package by.kes.altReality.data.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DateBreakdown extends TrackableObject<String> {

  private RealityQuantum realityQuantum;
  private CurrentSetup currentSetup;
}
