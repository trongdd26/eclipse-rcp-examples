package de.baeckerit.jface.databinding.util.binding;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.core.databinding.validation.IValidator;

import de.baeckerit.jface.databinding.util.DataBindingUtils;
import de.baeckerit.jface.databinding.util.IControlValues;
import de.baeckerit.jface.databinding.util.converter.TrimConverter;
import de.baeckerit.jface.databinding.util.validator.LengthValidator;

public class TextValueBindingFactory extends DefaultValueBindingFactory {
  public static final TextValueBindingFactory FACTORY = new TextValueBindingFactory();

  public int getMaxLength() {
    return Integer.MAX_VALUE;
  }

  public String getInputTooLongMessage() {
    return null;
  }

  @Override
  public IValidator getTargetToModelAfterGetValidator() {
    return new LengthValidator(getMaxLength(), getInputTooLongMessage());
  }

  @Override
  public IConverter getTargetToModelConverter() {
    return new TrimConverter();
  }

  @Override
  public void postProcessTarget(DataBindingContext dbc, Binding binding, IControlValues values) {
    DataBindingUtils.enablePostFormatting(dbc, binding, values.getFocusObservable());
  }
}
