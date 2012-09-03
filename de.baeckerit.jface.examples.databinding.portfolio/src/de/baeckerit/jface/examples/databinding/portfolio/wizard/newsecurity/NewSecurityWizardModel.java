package de.baeckerit.jface.examples.databinding.portfolio.wizard.newsecurity;

import java.util.Date;

import org.eclipse.core.databinding.ValidationStatusProvider;
import org.eclipse.core.databinding.observable.value.ComputedValue;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.databinding.validation.MultiValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;

import de.baeckerit.jface.examples.databinding.portfolio.data.ISecurity;
import de.baeckerit.jface.examples.databinding.portfolio.data.ISecurityDirection;
import de.baeckerit.jface.examples.databinding.portfolio.data.ISecurityType;

public class NewSecurityWizardModel {
  private final IObservableValue securityType = new WritableValue(null, ISecurityType.class);
  private final IObservableValue securityDirection = new WritableValue(null, ISecurityDirection.class);
  private final IObservableValue securityName = new WritableValue(null, String.class);
  private final IObservableValue isin = new WritableValue(null, String.class);
  private final IObservableValue firstTradingDay = new WritableValue(null, Date.class);
  private final IObservableValue lastTradingDay = new WritableValue(null, Date.class);

  private final IObservableValue complete = new ComputedValue(Boolean.class) {
    protected Object calculate() {
      if (securityType.getValue() == null) {
        return Boolean.FALSE;
      }
      if (securityName.getValue() == null || ((String) securityName.getValue()).isEmpty()) {
        return Boolean.FALSE;
      }
      if (isin.getValue() == null || ((String) isin.getValue()).isEmpty()) {
        return Boolean.FALSE;
      }
      if (firstTradingDay.getValue() == null) {
        return Boolean.FALSE;
      }
      if (requiresDirection() && securityDirection.getValue() == null) {
        return Boolean.FALSE;
      }
      return Boolean.TRUE;
    }
  };

  private final IObservableValue directionEnabled = new ComputedValue(Boolean.class) {
    protected Object calculate() {
      securityDirection.setValue(null);
      return Boolean.valueOf(requiresDirection());
    }
  };

  private final ValidationStatusProvider tradingRangeValidator = new MultiValidator() {
    protected IStatus validate() {
      Date startDate = (Date) firstTradingDay.getValue();
      Date endDate = (Date) lastTradingDay.getValue();
      if (startDate != null && endDate != null && startDate.compareTo(endDate) > 0)
        return ValidationStatus.error("Last trading day is before first trading day!");
      return ValidationStatus.ok();
    }
  };

  public boolean requiresDirection() {
    ISecurityType value = (ISecurityType) securityType.getValue();
    return value != null && ("OPT".equals(value.getPrimaryKey()) || "ETF".equals(value.getPrimaryKey()));
  }

  public IObservableValue getSecurityType() {
    return securityType;
  }

  public IObservableValue getSecurityDirection() {
    return securityDirection;
  }

  public IObservableValue getSecurityName() {
    return securityName;
  }

  public IObservableValue getIsin() {
    return isin;
  }

  public IObservableValue getFirstTradingDay() {
    return firstTradingDay;
  }

  public IObservableValue getLastTradingDay() {
    return lastTradingDay;
  }

  public IObservableValue getComplete() {
    return complete;
  }

  public IObservableValue getDirectionEnabled() {
    return directionEnabled;
  }

  public ValidationStatusProvider getTradingRangeValidator() {
    return tradingRangeValidator;
  }

  public void fillParams(ISecurity security) {
    security.setSecurityType((ISecurityType) securityType.getValue());
    security.setSecurityDirection((ISecurityDirection) securityDirection.getValue());
    security.setSecurityName((String) securityName.getValue());
    security.setIsin((String) isin.getValue());
    security.setFirstTradingDay((Date) firstTradingDay.getValue());
    security.setLastTradingDay((Date) lastTradingDay.getValue());
  }
}