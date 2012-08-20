package de.baeckerit.jdk.util.foco;

import de.baeckerit.jdk.util.Utils;
import de.baeckerit.jdk.util.getter.IGetter;

public abstract class AbstractComparableFoCo<T extends Comparable<T>> implements IFoCo, IGetter<T, Object> {

  private final boolean nullIsGreater;

  protected AbstractComparableFoCo(boolean nullIsGreater) {
    this.nullIsGreater = nullIsGreater;
  }

  public abstract T get(Object object);

  @Override
  public int compare(Object left, Object right) {
    return Utils.compare(get(left), get(right), nullIsGreater);
  }
}
