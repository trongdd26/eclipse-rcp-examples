package de.baeckerit.jface.examples.databinding.portfolio.wizard.newposition;

import java.util.Date;

import org.eclipse.core.databinding.observable.value.ComputedValue;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;

import de.baeckerit.jdk.util.Utils;
import de.baeckerit.jface.examples.databinding.portfolio.access.SecurityPositionParams;
import de.baeckerit.jface.examples.databinding.portfolio.viewable.ViewableSecurity;

public class NewPositionWizardModel {
	private final IObservableValue selectedSecurity = new WritableValue(null, ViewableSecurity.class);
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

	public SecurityPositionParams getParams() {
		SecurityPositionParams p = new SecurityPositionParams();
		p.securityKey = ((ViewableSecurity) selectedSecurity.getValue()).getSecurity().getPrimaryKey();
		p.buy = (Boolean) action.getValue();
		p.openDate = Utils.toDate((Date) openDate.getValue());
		return p;
	}
}