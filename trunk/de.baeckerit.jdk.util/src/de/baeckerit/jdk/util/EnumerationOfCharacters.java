package de.baeckerit.jdk.util;

public class EnumerationOfCharacters implements SetOfCharacters {

  private final char[] characters;

  public EnumerationOfCharacters(char... characters) {
    if (characters == null)
      throw new NullPointerException("characters");
    this.characters = characters;
  }

  public EnumerationOfCharacters(String characters) {
    this(characters.toCharArray());
  }

  public String getCharacters() {
    return String.valueOf(characters);
  }

  @Override
  public boolean contains(char c) {
    for (int i = 0; i < characters.length; i++) {
      if (c == characters[i])
        return true;
    }
    return false;
  }
}
