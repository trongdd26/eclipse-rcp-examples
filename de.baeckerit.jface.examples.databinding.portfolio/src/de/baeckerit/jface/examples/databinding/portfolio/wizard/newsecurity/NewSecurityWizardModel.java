package de.baeckerit.jface.examples.databinding.portfolio.wizard.newsecurity;

import java.util.Date;

import org.eclipse.core.databinding.ValidationStatusProvider;
import org.eclipse.core.databinding.observable.value.ComputedValue;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.databinding.validation.MultiValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;

import de.baeckerit.jface.examples.databinding.portfolio.access.SecurityParams;
import de.baeckerit.jface.examples.databinding.portfolio.data.SecurityDirection;
import de.baeckerit.jface.examples.databinding.portfolio.data.SecurityType;

public class NewSecurityWizardModel {
	private final IObservableValue securityType = new WritableValue(null, SecurityType.class);
	private final IObservableValue securityDirection = new WritableValue(null, SecurityDirection.class);
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
		SecurityType value = (SecurityType) securityType.getValue();
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

	public SecurityParams getParams() {
		SecurityParams params = new SecurityParams();
		params.securityTypeKey = ((SecurityType) securityType.getValue()).getPrimaryKey();
		SecurityDirection securityDirectionValue = (SecurityDirection) securityDirection.getValue();
		params.securityDirectionKey = securityDirectionValue == null ? null : securityDirectionValue.getPrimaryKey();
		params.securityName = (String) securityName.getValue();
		params.isin = (String) isin.getValue();
		params.firstTradingDay = (Date) firstTradingDay.getValue();
		params.lastTradingDay = (Date) lastTradingDay.getValue();
		return params;
	}
}