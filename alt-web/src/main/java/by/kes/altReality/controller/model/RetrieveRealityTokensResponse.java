package by.kes.altReality.controller.model;

import java.util.List;
import java.util.Map;

import by.kes.altReality.controller.model.common.GenericResponse;
import by.kes.altReality.data.security.AccessToken;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RetrieveRealityTokensResponse extends GenericResponse {

  private Map<String, List<AccessToken>> tokens;
}
