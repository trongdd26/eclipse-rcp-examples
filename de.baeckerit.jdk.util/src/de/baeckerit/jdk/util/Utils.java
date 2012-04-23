package de.baeckerit.jdk.util;


public final class Utils {

  public static <T> boolean equals(T a, T b) {
    return a == b || (a != null && a.equals(b));
  }

  private Utils() {
  }
}
