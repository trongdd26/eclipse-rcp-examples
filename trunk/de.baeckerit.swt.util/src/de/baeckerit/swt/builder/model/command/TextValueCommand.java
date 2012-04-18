package de.baeckerit.swt.builder.model.command;

import de.baeckerit.swt.builder.model.text.TextValue;

public class TextValueCommand extends MvcCommand {

  private final TextValue textValue;
  private String newValue;

  public TextValueCommand(TextValue textValue) {
    this.textValue = textValue;
  }

  public TextValue getTextValue() {
    return textValue;
  }

  public String getNewValue() {
    return newValue;
  }

  public void setNewValue(String newValue) {
    this.newValue = newValue;
  }

  @Override
  public boolean execute() {
    if (newValue == null)
      throw new NullPointerException("newValue");
    return textValue.setValueAndNotify(newValue);
  }
}
