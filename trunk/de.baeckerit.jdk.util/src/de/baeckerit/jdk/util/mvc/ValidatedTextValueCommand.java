package de.baeckerit.jdk.util.mvc;

public class ValidatedTextValueCommand extends MvcCommand {

  private final ValidatedTextValue textValue;
  private String newValue;

  public ValidatedTextValueCommand(ValidatedTextValue textValue) {
    this.textValue = textValue;
  }

  public ValidatedTextValue getTextValue() {
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
