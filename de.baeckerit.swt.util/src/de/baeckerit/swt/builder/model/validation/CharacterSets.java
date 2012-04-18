package de.baeckerit.swt.builder.model.validation;

public class CharacterSets implements CharacterSet {

  private final CharacterSet[] sets;

  public CharacterSets(CharacterSet... sets) {
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
