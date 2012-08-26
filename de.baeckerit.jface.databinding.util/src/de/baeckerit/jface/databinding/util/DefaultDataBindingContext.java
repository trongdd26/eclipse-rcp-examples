package de.baeckerit.jface.databinding.util;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.IObservableValue;

import de.baeckerit.jface.databinding.util.binding.IValueBindingFactory;

public class DefaultDataBindingContext extends DataBindingContext {

  public DefaultDataBindingContext() {
  }

  public DefaultDataBindingContext(Realm validationRealm) {
    super(validationRealm);
  }

  public Binding bindValue(IObservableValue target, IObservableValue model, IValueBindingFactory factory) {
    return bindValue(target, model, factory.createTargetToModel(), factory.createModelToTarget());
  }

  public Binding bindValue(IControlValues targetValues, IObservableValue model, IValueBindingFactory factory) {
    Binding binding = bindValue(targetValues.getTextObservable(), model, factory);
    factory.postProcessTarget(this, binding, targetValues);
    return binding;
  }
}
