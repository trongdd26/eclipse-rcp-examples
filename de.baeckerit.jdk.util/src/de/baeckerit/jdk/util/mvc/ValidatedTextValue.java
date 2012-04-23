package de.baeckerit.jdk.util.mvc;

import de.baeckerit.jdk.util.CharacterSet;
import de.baeckerit.jdk.util.CharacterSetUtils;
import de.baeckerit.jdk.util.ErrorLogger;

public class ValidatedTextValue extends MvcValue<String, ValidatedTextValueListener> {

  private final int maxLength;
  private final CharacterSet validChars;
  private final TrackedValue<String> rawText;
  private String formatMessage;

  public ValidatedTextValue(MvcModel owner, String description, int maxLength) {
    this(owner, description, "", "", maxLength, MvcConstants.ANY_CHARACTER);
  }

  public ValidatedTextValue(MvcModel owner, String description, int maxLength, CharacterSet validChars) {
    this(owner, description, "", "", maxLength, validChars);
  }

  public ValidatedTextValue(MvcModel owner, String description, String defaultValue, String initialValue, int maxLength,
      CharacterSet validChars) {
    super(owner, description, defaultValue, initialValue);
    this.rawText = new TrackedValue<String>(defaultValue, initialValue);
    this.maxLength = maxLength;
    this.validChars = validChars;
    checkInvariant();
  }

  public int getMaxLength() {
    return maxLength;
  }

  public String getRawText() {
    return rawText.getCurrentValue();
  }

  public CharacterSet getValidChars() {
    return validChars;
  }

  public String getFormatMessage() {
    return formatMessage;
  }

  protected final boolean setFormatMessage(String formatMessage) {
    this.formatMessage = formatMessage;
    return false;
  }

  @Override
  public boolean isEmpty() {
    return getRawText().isEmpty();
  }

  @Override
  public String getIsEmptyMessage() {
      return "Das Eingabefeld \"" + getDescription() + "\" darf nicht leer sein!";
  }

  @Override
  public String getIsNotValidMessage() {
    if (isCharactersValid())
      return getFormatMessage();
    else
      return getHasInvalidCharactersMessage();
  }

  public String getHasInvalidCharactersMessage() {
      return "Das Eingabefeld \"" + getDescription() + "\" enthält ungültige Zeichen!";
  }

  @Override
  public boolean isChanged() {
    return isRawTextModified() || super.isChanged();
  }

  public boolean isRawTextModified() {
    return rawText.isChanged();
  }

  @Override
  public ValidatedTextValueCommand getChangeValueCommand() {
    return new ValidatedTextValueCommand(this);
  }

  @Override
  public boolean setValue(String newValue) {
    checkNotNullAndLength(newValue);

    boolean changed = rawText.setCurrentValue(newValue);
    boolean rawTextValid = isCharactersValid();
    boolean valid = rawTextValid;

    if (valid) {
      try {
        valid = isFormatValid();
      } catch (Throwable t) {
        valid = false;
        ErrorLogger.logError(ValidatedTextValue.class, "Could not validate format", t);
      }
    }

    setValid(valid);
    if (valid) {
      super.setValue(newValue);
    } else {
      super.rollbackValue();
    }

    return changed;
  }

  private boolean isCharactersValid() {
    return CharacterSetUtils.validateText(rawText.getCurrentValue(), validChars);
  }

  public boolean isFormatValid() {
    return true;
  }

  public int[] computeInvalidPositions() {
    return CharacterSetUtils.computeInvalidPositions(rawText.getCurrentValue(), validChars);
  }

  @Override
  public void reset() {
    super.reset();
    rawText.reset();
  }

  @Override
  protected void checkpoint() {
    super.checkpoint();
    rawText.checkpoint();
  }

  @Override
  protected void notifyListeners() {
    // Must update the text first
    if (rawText.isChanged()) {
      for (ValidatedTextValueListener listener : listeners) {
        try {
          listener.rawTextChanged(rawText.getCheckpointedValue(), rawText.getCurrentValue());
        } catch (Throwable t) {
          ErrorLogger.logListener(ValidatedTextValue.class, t);
        }
      }
    }

    super.notifyListeners();
  }

  private void checkInvariant() {
    if (getDefaultValue() == null)
      throw new NullPointerException("defaultValue");
    if (maxLength <= 0)
      throw new IllegalArgumentException("maxLength must be greater 0");
    checkNotNullAndLength(getValue());
    if (!CharacterSetUtils.validateText(getValue(), validChars))
      throw new IllegalStateException("invalid value");
    if (!CharacterSetUtils.validateText(getDefaultValue(), validChars))
      throw new IllegalStateException("invalid default value");
  }

  protected final void checkNotNullAndLength(String newValue) {
    if (newValue == null)
      throw new NullPointerException("newValue");
    if (maxLength < newValue.length())
      throw new IllegalLengthException(newValue, maxLength);
  }
}
