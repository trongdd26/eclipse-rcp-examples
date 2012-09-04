package de.baeckerit.jface.databinding.util.binding;

import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.osgi.util.NLS;

import de.baeckerit.jface.databinding.util.validator.LengthValidator;

/**
 * A text field binding is a text value binding with a length constraint.
 */
public class TextFieldBindingFactory extends TextValueBindingFactory {

  private static final String DEFAULT_MESSAGE = "Die Eingabe im Feld ''''{1}'''' darf nicht länger als {0} Zeichen sein!";

  private int maxLength;
  private String fieldName;

  public TextFieldBindingFactory(int maxLength, String fieldName) {
    this.maxLength = maxLength;
    this.fieldName = fieldName;
  }

  public int getMaxLength() {
    return maxLength;
  }

  public String getInputTooLongMessage() {
    return NLS.bind(DEFAULT_MESSAGE, "{0}", fieldName);
  }

  @Override
  public IValidator getTargetToModelAfterConvertValidator() {
    return new LengthValidator(getMaxLength(), getInputTooLongMessage());
  }
}
