package by.kes.altReality.data.dao.impl;

import static java.util.Arrays.asList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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

  public Map<String, List<AccessToken>> getRealityTokens() {
    return accessTokenDataStore.getAllRealityTokens();
  }

  public AccessToken matchesRealityToken(final String id, final String token) {
    return accessTokenDataStore.findRealityTokens(id, token).orElse(AccessToken.NULL_ACCESS_TOKEN);
  }

  public AccessToken matchesUserToken(final String token) {
    return accessTokenDataStore.getUserToken(token).orElse(AccessToken.NULL_ACCESS_TOKEN);
  }

}
