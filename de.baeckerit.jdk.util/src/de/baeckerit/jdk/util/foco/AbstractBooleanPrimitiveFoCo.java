package de.baeckerit.jdk.util.foco;

public abstract class AbstractBooleanPrimitiveFoCo extends AbstractBooleanFoCo {

  public abstract boolean getBoolean(Object object);

  public Boolean get(Object object) {
    return Boolean.valueOf(getBoolean(object));
  }

  public AbstractBooleanPrimitiveFoCo(String forTrue, String forFalse) {
    super(true, forTrue, forFalse, null);
  }

  public AbstractBooleanPrimitiveFoCo() {
    super(true, null);
  }
}
