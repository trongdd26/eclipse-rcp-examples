package de.baeckerit.swt.builder.model.text;

import de.baeckerit.swt.builder.model.MvcModel;
import de.baeckerit.swt.builder.model.MvcValue;
import de.baeckerit.swt.builder.model.command.TextValueCommand;
import de.baeckerit.swt.builder.model.exception.IllegalLengthException;
import de.baeckerit.swt.builder.model.listener.TextValueListener;

public class TextValue extends MvcValue<String, TextValueListener> {

  private final int maxLength;

  public TextValue(MvcModel owner, String description, int maxLength) {
    this(owner, description, "", "", maxLength);
  }

  public TextValue(MvcModel owner, String description, String defaultValue, String initialValue, int maxLength) {
    super(owner, description, defaultValue, initialValue);
    this.maxLength = maxLength;
    checkInvariant();
  }

  @Override
  public boolean isEmpty() {
    return getValue().isEmpty();
  }

  @Override
  public String getIsEmptyMessage() {
    return "Das Eingabefeld \"" + getDescription() + "\" darf nicht leer sein!";
  }

  @Override
  public boolean setValue(String newValue) {
    checkNotNullAndLength(newValue);
    return super.setValue(newValue);
  }

  public int getMaxLength() {
    return maxLength;
  }

  @Override
  public TextValueCommand getChangeValueCommand() {
    return new TextValueCommand(this);
  }

  private void checkInvariant() {
    if (getDefaultValue() == null)
      throw new NullPointerException("defaultValue");
    if (maxLength <= 0)
      throw new IllegalArgumentException("maxLength must be greater 0");
    checkNotNullAndLength(getValue());
  }

  protected final void checkNotNullAndLength(String newValue) {
    if (newValue == null)
      throw new NullPointerException("newValue");
    if (maxLength < newValue.length())
      throw new IllegalLengthException(newValue, getMaxLength());
  }
}
