package de.baeckerit.jdk.util.mvc;

public abstract class ValidatedTextValueListener extends TextValueListener {

  public abstract void rawTextChanged(String oldValue, String newValue);
}
