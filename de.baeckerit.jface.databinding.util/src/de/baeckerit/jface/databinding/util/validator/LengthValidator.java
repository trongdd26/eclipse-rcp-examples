package de.baeckerit.jface.databinding.util.validator;

import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.osgi.util.NLS;

public class LengthValidator implements IValidator {

  private static final String DEFAULT_MESSAGE = "Die Eingabe darf nicht länger als {0} Zeichen sein";

  private final int maxLength;
  private final String message;
  private final IValidator nestedValidator;

  public LengthValidator(int maxLength, String message, IValidator nestedValidator) {
    if (maxLength < 1) {
      throw new IllegalArgumentException("maxLength must be greater 0");
    }
    this.maxLength = maxLength;
    this.message = message == null ? DEFAULT_MESSAGE : message;
    this.nestedValidator = nestedValidator;
  }

  public LengthValidator(int maxLength, IValidator nestedValidator) {
    this(maxLength, null, nestedValidator);
  }

  public LengthValidator(int maxLength, String message) {
    this(maxLength, message, null);
  }

  public LengthValidator(int maxLength) {
    this(maxLength, null, null);
  }

  public int getMaxLength() {
    return maxLength;
  }

  public IValidator getNestedValidator() {
    return nestedValidator;
  }

  @Override
  public IStatus validate(Object value) {
    String input = (String) value;
    if (input != null) {
      String trimmed = input.trim();
      if (trimmed.length() > maxLength) {
        return ValidationStatus.error(NLS.bind(message, maxLength));
      }
    }
    return nestedValidator == null ? ValidationStatus.OK_STATUS : nestedValidator.validate(value);
  }
}
