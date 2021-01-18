package by.kes.altReality.controller.model;

import java.util.Collection;

import by.kes.altReality.data.domain.RealityCharacteristics;
import lombok.Data;

@Data
public class GetRealityCharacteristicsResponse {

  private Collection<RealityCharacteristics> realities;
}
