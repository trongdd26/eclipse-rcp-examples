package de.baeckerit.jface.databinding.util.binding;

import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.core.databinding.validation.IValidator;

import de.baeckerit.jface.databinding.util.converter.DateToStringConverter;
import de.baeckerit.jface.databinding.util.converter.StringToDateConverter;
import de.baeckerit.jface.databinding.util.validator.DateValidator;

public class DateValueBindingFactory extends TextValueBindingFactory {
  public static final DateValueBindingFactory FACTORY = new DateValueBindingFactory();

  @Override
  public IValidator getTargetToModelAfterGetValidator() {
    return DateValidator.INSTANCE;
  }

  @Override
  public IConverter getTargetToModelConverter() {
    return StringToDateConverter.INSTANCE;
  }

  @Override
  public IConverter getModelToTargetConverter() {
    return DateToStringConverter.INSTANCE;
  }
}
