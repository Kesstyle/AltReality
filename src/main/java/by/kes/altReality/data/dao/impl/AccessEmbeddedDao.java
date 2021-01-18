package by.kes.altReality.data.dao.impl;

import static java.util.Arrays.asList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import by.kes.altReality.data.persistence.AccessTokenDataStore;
import by.kes.altReality.data.security.AccessToken;

@Repository
public class AccessEmbeddedDao {

  @Autowired
  private AccessTokenDataStore accessTokenDataStore;

  public boolean saveRealityToken(final String realityId, final List<AccessToken> accessTokens) {
    accessTokenDataStore.addRealityTokens(realityId, accessTokens);
    return true;
  }

  public boolean saveRealityToken(final String realityId, final AccessToken accessToken) {
    return saveRealityToken(realityId, asList(accessToken));
  }

  public void saveUserToken(final AccessToken accessToken) {
    accessTokenDataStore.addUserToken(accessToken);
  }

  public List<AccessToken> get(final String id) {
    return accessTokenDataStore.getRealityToken(id).orElse(null);
  }

  public AccessToken matchesToken(final String id, final String token) {
    return accessTokenDataStore.findRealityTokens(id, token);
  }

  public AccessToken matchesUserToken(final String token) {
    return accessTokenDataStore.getUserToken(token).orElse(null);
  }
}
