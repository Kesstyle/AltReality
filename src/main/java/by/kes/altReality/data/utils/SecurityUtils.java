package by.kes.altReality.data.utils;

import static by.kes.altReality.data.security.AccessRight.GUEST;
import static by.kes.altReality.data.security.AccessRight.isHigherOrEqual;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import by.kes.altReality.data.dao.impl.AccessEmbeddedDao;
import by.kes.altReality.data.security.AccessRight;
import by.kes.altReality.data.security.AccessToken;
import by.kes.altReality.service.exception.RealityActionNotAllowedException;

@Component
public class SecurityUtils {

  private static final String DEFAULT_ERROR = "Access denied";

  @Autowired
  private AccessEmbeddedDao accessDao;

  public void assertUserRights(final String errorMessage, final String token, final AccessRight expected) {
    final AccessToken accessToken = accessDao.matchesUserToken(token);
    if (!isHigherOrEqual(accessToken.getAccessRight(), expected)) {
      throw new RealityActionNotAllowedException(errorMessage);
    }
  }

  public void assertUserRights(final String token, final AccessRight expected) {
    assertUserRights(DEFAULT_ERROR, token, expected);
  }

  public void assertRealityRights(final String errorMessage, final String realityToken,
                               final String realityId, final AccessRight expected) {
    final AccessToken accessToken = accessDao.matchesRealityToken(realityId, realityToken);
    if (!isHigherOrEqual(accessToken.getAccessRight(), expected)) {
      throw new RealityActionNotAllowedException(errorMessage);
    }
  }

  public void assertRealityRights(final String realityToken,
                                     final String realityId, final AccessRight expected) {
    assertRealityRights(DEFAULT_ERROR, realityToken, realityId, expected);
  }

  public void assertRealityRights(final String realityToken, final String realityId) {
    assertRealityRights(realityToken, realityId, GUEST);
  }

  public void assertRealityRights(final String errorMessage, final String realityToken, final String realityId) {
    assertRealityRights(errorMessage, realityToken, realityId, GUEST);
  }
}
