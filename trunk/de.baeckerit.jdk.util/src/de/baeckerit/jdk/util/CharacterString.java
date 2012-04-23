package de.baeckerit.jdk.util;

public class CharacterString implements CharacterSet {

  private final String characters;

  public CharacterString(String characters) {
    if (characters == null)
      throw new NullPointerException("characters");
    this.characters = characters;
  }

  public String getCharacters() {
    return characters;
  }

  @Override
  public boolean contains(char c) {
    for (int i = 0; i < characters.length(); i++) {
      if (c == characters.charAt(i))
        return true;
    }
    return false;
  }
}
