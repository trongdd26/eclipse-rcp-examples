package de.baeckerit.jface.databinding.util;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.core.runtime.IStatus;

public class DataBindingUtils {

  public static void enablePostFormatting(final DataBindingContext dbc, final Binding binding, final IObservableValue focusObservable) {
    focusObservable.addValueChangeListener(new IValueChangeListener() {
      public void handleValueChange(final ValueChangeEvent event) {
        final Boolean focusWhenFired = (Boolean) (event.diff.getNewValue());
        dbc.getValidationRealm().asyncExec(new Runnable() {
          public void run() {
            if (Boolean.FALSE.equals(focusWhenFired) && !binding.isDisposed()) {
              if (((IStatus) binding.getValidationStatus().getValue()).isOK()) {
                binding.updateModelToTarget();
              }
            }
          }
        });
      }
    });
  }
}
