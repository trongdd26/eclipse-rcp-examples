package de.baeckerit.jdk.util.foco;

import de.baeckerit.jdk.util.IProvidesDisplayName;
import de.baeckerit.jdk.util.getter.IStringFromObjectGetter;

public class DisplayNameFoCo extends AbstractStringFoCo implements IStringFromObjectGetter {

  public static final DisplayNameFoCo INSTANCE = new DisplayNameFoCo();

  protected DisplayNameFoCo() {
    super(true);
  }

  @Override
  public String get(Object object) {
    return ((IProvidesDisplayName) object).getDisplayName();
  }
}
