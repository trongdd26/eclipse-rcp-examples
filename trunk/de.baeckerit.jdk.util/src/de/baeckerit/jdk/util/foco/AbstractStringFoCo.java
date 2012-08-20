package de.baeckerit.jdk.util.foco;

public abstract class AbstractStringFoCo extends AbstractComparableFoCo<String> {
  public abstract String get(Object object);

  protected AbstractStringFoCo() {
    super(true);
  }

  protected AbstractStringFoCo(boolean nullIsAfter) {
    super(nullIsAfter);
  }

  @Override
  public String format(Object object) {
    return get(object);
  }
}
