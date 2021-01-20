package by.kes.altReality.data.utils;

import org.springframework.stereotype.Component;

import by.kes.altReality.data.domain.DateBreakdown;

@Component
public class RealityUtils {

  private static final String IDS_SEPARATOR = "::";

  public String getRealityIdFromDateBreakdown(final DateBreakdown dateBreakdown) {
    if (dateBreakdown == null || dateBreakdown.getId() == null) {
      return null;
    }
    final String[] ids = dateBreakdown.getId().split(IDS_SEPARATOR);
    return ids[0];
  }

  public String assignBreakdownId(final String currentId, final String breakdownId) {
    if (currentId == null) {
      return null;
    }
    final String[] ids = currentId.split(IDS_SEPARATOR);
    if (ids.length > 1) {
      return currentId;
    }
    return currentId + IDS_SEPARATOR + breakdownId;
  }
}
