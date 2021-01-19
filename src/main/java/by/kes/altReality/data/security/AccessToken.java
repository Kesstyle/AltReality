package by.kes.altReality.data.security;

import static by.kes.altReality.data.security.AccessRight.NONE;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccessToken {

  private String token;
  private String realityId;
  private AccessRight accessRight;
  private AccessRestriction accessRestriction;

  public static final AccessToken NULL_ACCESS_TOKEN = new NullAccessToken();

  static class NullAccessToken extends AccessToken {

      private NullAccessToken() {
        super(null, null, NONE, null);
      }
  }
}
