package de.baeckerit.jface.databinding.util.binding;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.conversion.IConverter;

import de.baeckerit.jface.databinding.util.DataBindingUtils;
import de.baeckerit.jface.databinding.util.IControlValues;
import de.baeckerit.jface.databinding.util.converter.TrimConverter;

public class TextValueBindingFactory extends DefaultValueBindingFactory {
  public static final TextValueBindingFactory FACTORY = new TextValueBindingFactory();

  @Override
  public IConverter getTargetToModelConverter() {
    return new TrimConverter();
  }

  @Override
  public void postProcessTarget(DataBindingContext dbc, Binding binding, IControlValues values) {
    DataBindingUtils.enablePostFormatting(dbc, binding, values.getFocusObservable());
  }
}
