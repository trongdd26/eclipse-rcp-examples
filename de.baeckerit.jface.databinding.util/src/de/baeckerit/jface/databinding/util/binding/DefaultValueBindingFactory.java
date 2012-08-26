package de.baeckerit.jface.databinding.util.binding;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.core.databinding.validation.IValidator;

import de.baeckerit.jface.databinding.util.IControlValues;

public class DefaultValueBindingFactory implements IValueBindingFactory {

  public DefaultValueBindingFactory() {
    super();
  }

  @Override
  public UpdateValueStrategy createTargetToModel() {
    return new UpdateValueStrategy().setAfterGetValidator(getTargetToModelAfterGetValidator())
        .setAfterConvertValidator(getTargetToModelAfterConvertValidator()).setBeforeSetValidator(getTargetToModelBeforeSetValidator())
        .setConverter(getTargetToModelConverter());
  }

  @Override
  public UpdateValueStrategy createModelToTarget() {
    return new UpdateValueStrategy().setAfterGetValidator(getModelToTargetAfterGetValidator())
        .setAfterConvertValidator(getModelToTargetAfterConvertValidator()).setBeforeSetValidator(getModelToTargetBeforeSetValidator())
        .setConverter(getModelToTargetConverter());
  }

  public IValidator getTargetToModelAfterGetValidator() {
    return null;
  }

  public IValidator getModelToTargetAfterGetValidator() {
    return null;
  }

  public IValidator getTargetToModelAfterConvertValidator() {
    return null;
  }

  public IValidator getModelToTargetAfterConvertValidator() {
    return null;
  }

  public IValidator getTargetToModelBeforeSetValidator() {
    return null;
  }

  public IValidator getModelToTargetBeforeSetValidator() {
    return null;
  }

  public IConverter getTargetToModelConverter() {
    return null;
  }

  public IConverter getModelToTargetConverter() {
    return null;
  }

  @Override
  public void postProcessTarget(DataBindingContext dbc, Binding binding, IControlValues values) {
  }
}