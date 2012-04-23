package de.baeckerit.jdk.util.mvc;


public abstract class TextValueListener extends MvcValueListener<String> {

  @Override
  public abstract void valueChanged(String oldValue, String newValue);
}
