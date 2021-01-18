package by.kes.altReality.data.security;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccessToken {

  private String token;
  private String realityId;
  private AccessRight accessRight;
  private AccessRestriction accessRestriction;
}
