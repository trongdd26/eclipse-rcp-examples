package de.baeckerit.swt.builder.model.listener;


public abstract class TextValueListener extends MvcValueListener<String> {

  @Override
  public abstract void valueChanged(String oldValue, String newValue);
}
