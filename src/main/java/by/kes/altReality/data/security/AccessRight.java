package by.kes.altReality.data.security;

public enum AccessRight {
  NONE, GUEST, USER, OWNER, ADMIN;

  public static boolean isHigherOrEqual(final AccessRight current, final AccessRight compareTo) {
    if (current == null || compareTo == null) {
      return false;
    }
    return current.ordinal() >= compareTo.ordinal();
  }
}
