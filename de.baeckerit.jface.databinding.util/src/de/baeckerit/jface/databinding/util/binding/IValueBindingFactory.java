package de.baeckerit.jface.databinding.util.binding;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;

import de.baeckerit.jface.databinding.util.IControlValues;

public interface IValueBindingFactory {
  UpdateValueStrategy createTargetToModel();

  UpdateValueStrategy createModelToTarget();

  void postProcessTarget(DataBindingContext dbc, Binding binding, IControlValues values);
}
