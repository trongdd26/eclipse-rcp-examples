package de.baeckerit.jdk.util;

import java.util.Arrays;

public final class CharacterSetUtils {

  public static boolean validateText(String toValidate, CharacterSet validChars) {
    for (int i = 0, max = toValidate.length(); i < max; i++) {
      if (!validChars.contains(toValidate.charAt(i)))
        return false;
    }
    return true;
  }

  public static int[] computeInvalidPositions(String toValidate, CharacterSet validChars) {
    final int[] pos = new int[toValidate.length()];

    int count = 0;
    for (int i = 0, max = toValidate.length(); i < max; i++) {
      if (!validChars.contains(toValidate.charAt(i))) {
        pos[count++] = i;
      }
    }

    return Arrays.copyOf(pos, count);
  }
}
