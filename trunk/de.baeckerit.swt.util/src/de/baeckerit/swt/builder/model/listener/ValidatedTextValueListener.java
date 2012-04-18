package de.baeckerit.swt.builder.model.listener;

public abstract class ValidatedTextValueListener extends TextValueListener {

  public abstract void rawTextChanged(String oldValue, String newValue);
}
