package de.baeckerit.jface.databinding.util;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.databinding.dialog.DialogPageSupport;
import org.eclipse.jface.wizard.WizardPage;

public class WizardPageSupport extends DialogPageSupport {

  private static final int DEFAULT_SEVERITY_MASK = IStatus.ERROR | IStatus.CANCEL;

  private final IObservableValue[] completenessProviders;

  private final IValueChangeListener completenessProviderListener = new IValueChangeListener() {

    @Override
    public void handleValueChange(ValueChangeEvent event) {
      computePageComplete();
    }
  };

  public WizardPageSupport(WizardPage wizardPage, DataBindingContext dbc, IObservableValue... completenessProviders) {
    super(wizardPage, dbc);
    this.completenessProviders = completenessProviders;
    for (int i = 0; i < completenessProviders.length; i++) {
      completenessProviders[i].addValueChangeListener(completenessProviderListener);
    }
    computePageComplete();
  }

  public int getSeverityMask() {
    return DEFAULT_SEVERITY_MASK;
  }

  protected void handleStatusChanged() {
    super.handleStatusChanged();
    computePageComplete();
  }

  public void computePageComplete() {
    boolean contextValid = true;
    if (currentStatusStale) {
      contextValid = false;
    } else if (currentStatus != null) {
      contextValid = !currentStatus.matches(getSeverityMask());
    }
    if (contextValid && completenessProviders != null) {
      for (int i = 0; contextValid && i < completenessProviders.length; i++) {
        Object value = completenessProviders[i].getValue();
        if (value == null || value.getClass() != Boolean.class || !value.equals(Boolean.TRUE)) {
          contextValid = false;
        }
      }
    }
    ((WizardPage) getDialogPage()).setPageComplete(contextValid);
  }

  @Override
  public void dispose() {
    for (int i = 0; i < completenessProviders.length; i++) {
      completenessProviders[i].removeValueChangeListener(completenessProviderListener);
    }
    super.dispose();
  }
}
