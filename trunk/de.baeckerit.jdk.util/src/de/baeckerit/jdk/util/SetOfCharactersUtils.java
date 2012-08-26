package de.baeckerit.jdk.util;

import java.util.Arrays;

public final class SetOfCharactersUtils {

  public static final SetOfCharacters ANY_CHARACTER = new SetOfCharacters() {
    public boolean contains(char ch) {
      return Character.isDefined(ch);
    }
  };
  public static final SetOfCharacters ANY_DIGIT = new SetOfCharacters() {
    public boolean contains(char ch) {
      return Character.isDigit(ch);
    }
  };
  public static final SetOfCharacters ANY_UPPER = new SetOfCharacters() {
    public boolean contains(char ch) {
      return Character.isUpperCase(ch);
    }
  };
  public static final SetOfCharacters ANY_LOWER = new SetOfCharacters() {
    public boolean contains(char ch) {
      return Character.isLowerCase(ch);
    }
  };
  public static final SetOfCharacters ANY_LETTER = new SetOfCharacters() {
    public boolean contains(char ch) {
      return Character.isLetter(ch);
    }
  };
  public static final SetOfCharacters LETTERS_AND_DIGITS = new SetOfCharacters() {
    public boolean contains(char ch) {
      return Character.isLetterOrDigit(ch);
    }
  };
  public static final EnumerationOfCharacters SPECIAL_CHARACTERS = new EnumerationOfCharacters(" !\"§$%&/()=?\\+*#-_,.;:'~@€|<>[]{}");

  public static final SetOfCharacters DE_LOWER = new RangeOfCharacters('a', 'z');
  public static final SetOfCharacters DE_UPPER = new RangeOfCharacters('A', 'Z');
  public static final SetOfCharacters DE_DIGIT = new RangeOfCharacters('0', '9');
  public static final SetOfCharacters DE_UMLAUT_LOWER = new EnumerationOfCharacters("äöüß");
  public static final SetOfCharacters DE_UMLAUT_UPPER = new EnumerationOfCharacters("ÄÖÜ");
  public static final SetOfCharacters DE_UMLAUT = new EnumerationOfCharacters("äöüßÄÖÜ");
  public static final SetOfCharacters DE_LETTERS = new SetsOfCharacters(DE_LOWER, DE_UPPER, DE_UMLAUT);
  public static final SetOfCharacters DE_LETTERS_AND_DIGITS = new SetsOfCharacters(DE_LETTERS, DE_DIGIT, DE_UMLAUT);

  public static boolean validateText(String toValidate, SetOfCharacters validChars) {
    for (int i = 0, max = toValidate.length(); i < max; i++) {
      if (!validChars.contains(toValidate.charAt(i)))
        return false;
    }
    return true;
  }

  public static int[] computeInvalidPositions(String toValidate, SetOfCharacters validChars) {
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
