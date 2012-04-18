package de.baeckerit.swt.builder.model.validation;

public class CharacterEnumeration implements CharacterSet {

  private final char[] characters;

  public CharacterEnumeration(char... characters) {
    if (characters == null)
      throw new NullPointerException("characters");
    this.characters = characters;
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
