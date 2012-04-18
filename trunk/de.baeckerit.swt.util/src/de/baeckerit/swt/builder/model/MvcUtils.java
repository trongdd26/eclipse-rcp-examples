package de.baeckerit.swt.builder.model;


public final class MvcUtils {

  public static <T> boolean equals(T a, T b) {
    return a == b || (a != null && a.equals(b));
  }

  private MvcUtils() {
  }
}
