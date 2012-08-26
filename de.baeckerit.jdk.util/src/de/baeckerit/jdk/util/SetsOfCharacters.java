package de.baeckerit.jdk.util;

public class SetsOfCharacters implements SetOfCharacters {

  private final SetOfCharacters[] sets;

  public SetsOfCharacters(SetOfCharacters... sets) {
    if (sets == null)
      throw new NullPointerException("sets");
    this.sets = sets;
  }

  @Override
  public boolean contains(char c) {
    for (int i = 0; i < sets.length; i++) {
      if (sets[i].contains(c))
        return true;
    }
    return false;
  }
}
