package de.baeckerit.jface.examples.databinding.portfolio.wizard.newposition;

import java.util.Date;

import org.eclipse.core.databinding.observable.value.ComputedValue;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;

import de.baeckerit.jdk.util.Utils;
import de.baeckerit.jface.examples.databinding.portfolio.data.Security;
import de.baeckerit.jface.examples.databinding.portfolio.data.SecurityPosition;

public class NewPositionWizardModel {
  private final IObservableValue selectedSecurity = new WritableValue(null, Security.class);
  private final IObservableValue action = new WritableValue(null, Boolean.class);
  private final IObservableValue openDate = new WritableValue(null, Date.class);

  private final IObservableValue selectionPageComplete = new ComputedValue(Boolean.class) {
    protected Object calculate() {
      if (selectedSecurity.getValue() == null) {
        return Boolean.FALSE;
      }
      return Boolean.TRUE;
    }
  };

  private final IObservableValue attributesPageComplete = new ComputedValue(Boolean.class) {
    protected Object calculate() {
      if (action.getValue() == null) {
        return Boolean.FALSE;
      }
      if (openDate.getValue() == null) {
        return Boolean.FALSE;
      }
      return Boolean.TRUE;
    }
  };

  public IObservableValue getSelectedSecurity() {
    return selectedSecurity;
  }

  public IObservableValue getAction() {
    return action;
  }

  public IObservableValue getOpenDate() {
    return openDate;
  }

  public IObservableValue getSelectionPageComplete() {
    return selectionPageComplete;
  }

  public IObservableValue getAttributesPageComplete() {
    return attributesPageComplete;
  }

  public SecurityPosition getParams() {
    SecurityPosition p = new SecurityPosition();
    p.setSecurity((Security) selectedSecurity.getValue());
    p.setBuy((Boolean) action.getValue());
    p.setOpenDate(Utils.toDate((Date) openDate.getValue()));
    p.setClosingDate(null);
    return p;
  }
}