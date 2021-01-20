package by.kes.altReality.controller.model;

import java.util.Collection;

import by.kes.altReality.controller.model.common.GenericResponse;
import by.kes.altReality.data.domain.RealityCharacteristics;
import lombok.Data;

@Data
public class GetRealityCharacteristicsResponse extends GenericResponse {

  private Collection<RealityCharacteristics> realities;
}
