package by.kes.altReality.data.persistence;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import by.kes.altReality.data.security.AccessToken;

@Repository
public class AccessTokenDataStore {

  private final Map<String, List<AccessToken>> realityTokens = new ConcurrentHashMap<>();
  private final Map<String, AccessToken> userTokens = new ConcurrentHashMap<>();

  public Optional<List<AccessToken>> getRealityToken(final String id) {
    return Optional.ofNullable(realityTokens.get(id));
  }

  public Optional<AccessToken> getUserToken(final String id) {
    return Optional.ofNullable(userTokens.get(id));
  }

  public void addRealityTokens(final String id, final Collection<AccessToken> accessTokens) {
    final List<AccessToken> accessTokenList = getRealityToken(id).orElse(new ArrayList<>());
    accessTokenList.addAll(accessTokens);
    realityTokens.put(id, accessTokenList);
  }

  public AccessToken findRealityTokens(final String id, final String token) {
    return getRealityToken(id).orElse(new ArrayList<>()).stream()
        .filter(accessToken -> accessToken.getToken().equalsIgnoreCase(token))
        .findFirst()
        .orElse(null);
  }

  public void addUserToken(final AccessToken accessToken) {
    if (accessToken != null && accessToken.getToken() != null) {
      userTokens.put(accessToken.getToken(), accessToken);
    }
  }
}
