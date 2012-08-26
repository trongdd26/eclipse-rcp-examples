package de.baeckerit.jface.databinding.util.binding;

import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.core.databinding.validation.IValidator;

import de.baeckerit.jface.databinding.util.converter.ToUpperCaseConverter;
import de.baeckerit.jface.databinding.util.converter.TrimConverter;
import de.baeckerit.jface.databinding.util.validator.IsinValidator;

public class IsinValueBindingFactory extends TextValueBindingFactory {
  public static final IsinValueBindingFactory FACTORY = new IsinValueBindingFactory();

  @Override
  public IConverter getTargetToModelConverter() {
    return new TrimConverter(new ToUpperCaseConverter());
  }

  @Override
  public IValidator getTargetToModelAfterConvertValidator() {
    return new IsinValidator();
  }
}
