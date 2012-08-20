package de.baeckerit.jdk.util.foco;

import de.baeckerit.jdk.util.getter.IGetter;

public abstract class AbstractBooleanFoCo extends AbstractComparableFoCo<Boolean> implements IGetter<Boolean, Object> {
  private final String forTrue;
  private final String forFalse;
  private final String forNull;

  public abstract Boolean get(Object object);

  protected AbstractBooleanFoCo(boolean nullIsAfter, String forTrue, String forFalse, String forNull) {
    super(nullIsAfter);
    this.forTrue = forTrue;
    this.forFalse = forFalse;
    this.forNull = forNull;
  }

  protected AbstractBooleanFoCo(boolean nullIsAfter, String forNull) {
    this(nullIsAfter, Boolean.TRUE.toString(), Boolean.FALSE.toString(), forNull);
  }

  protected AbstractBooleanFoCo() {
    this(true, "NULL");
  }

  @Override
  public String format(Object object) {
    Boolean value = get(object);
    return value == null ? forNull : value.booleanValue() ? forTrue : forFalse;
  }

  @Override
  public int compare(Object left, Object right) {
    return get(left).compareTo(get(right));
  }
}
