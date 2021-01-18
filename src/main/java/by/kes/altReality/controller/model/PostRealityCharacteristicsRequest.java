package by.kes.altReality.controller.model;

import java.util.Collection;

import by.kes.altReality.data.domain.DateBreakdown;
import by.kes.altReality.data.domain.RealityCharacteristics;
import lombok.Data;

@Data
public class PostRealityCharacteristicsRequest {

  private Collection<DateBreakdown> dateBreakdowns;
}
