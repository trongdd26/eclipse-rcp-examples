package de.baeckerit.swt.builder.model.listener;

public abstract class MvcValueListener<T> extends MvcPropertyListener {
  public abstract void valueChanged(T previousValue, T newValue);
}
