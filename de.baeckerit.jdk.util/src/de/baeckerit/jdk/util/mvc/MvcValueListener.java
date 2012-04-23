package de.baeckerit.jdk.util.mvc;

public abstract class MvcValueListener<T> extends MvcPropertyListener {
  public abstract void valueChanged(T previousValue, T newValue);
}
