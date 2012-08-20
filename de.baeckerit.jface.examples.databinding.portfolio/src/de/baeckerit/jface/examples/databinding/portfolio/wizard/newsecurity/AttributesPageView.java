package de.baeckerit.jface.examples.databinding.portfolio.wizard.newsecurity;

import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.viewers.IViewerObservableValue;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import de.baeckerit.jface.databinding.util.ControlValues;
import de.baeckerit.jface.databinding.util.IControlValues;
import de.baeckerit.jface.examples.databinding.portfolio.ServiceLocator;
import de.baeckerit.jface.examples.databinding.portfolio.view.PmWizardPageView;
import de.baeckerit.jface.util.DisplayNameLabelProvider;
import de.baeckerit.swt.util.SWTUtils;

public class AttributesPageView extends PmWizardPageView {

	private final Composite control;
	private final IControlValues directionValues;
	private final IControlValues securityNameValues;
	private final IControlValues isinValues;
	private final IControlValues firstTradingDayValues;
	private final IControlValues lastTradingDayValues;
	private final IViewerObservableValue securityTypeObservable;
	private final IViewerObservableValue directionObservable;

	public AttributesPageView(Composite parent) {
		super(parent);

		control = new Composite(parent, SWT.NONE);
		new Label(control, SWT.NONE).setText("Security Type:");
		Combo typeCombo = new Combo(control, SWT.READ_ONLY);

		new Label(control, SWT.NONE).setText("Direction:");
		Combo directionCombo = new Combo(control, SWT.READ_ONLY);

		new Label(control, SWT.NONE).setText("Security Name:");
		Text securityNameText = new Text(control, SWT.BORDER);

		new Label(control, SWT.NONE).setText("ISIN:");
		Text isinText = new Text(control, SWT.BORDER);

		new Label(control, SWT.NONE).setText("First Trading Day:");
		Text firstTradingDayText = new Text(control, SWT.BORDER);

		new Label(control, SWT.NONE).setText("Last Trading Day:");
		Text lastTradingDayText = new Text(control, SWT.BORDER);

		SWTUtils.enableSelectAllOnFocusIn(securityNameText, isinText, firstTradingDayText, lastTradingDayText);

		ComboViewer typesViewer = new ComboViewer(typeCombo);
		typesViewer.setContentProvider(new ArrayContentProvider());
		typesViewer.setLabelProvider(new DisplayNameLabelProvider());
		typesViewer.setComparator(new ViewerComparator());
		typesViewer.setInput(ServiceLocator.getDataAccess().getSecurityTypes());

		ComboViewer directionViewer = new ComboViewer(directionCombo);
		directionViewer.setContentProvider(new ArrayContentProvider());
		directionViewer.setLabelProvider(new DisplayNameLabelProvider());
		directionViewer.setComparator(new ViewerComparator());
		directionViewer.setInput(ServiceLocator.getDataAccess().getSecurityDirections());

		securityTypeObservable = ViewersObservables.observeSingleSelection(typesViewer);
		directionObservable = ViewersObservables.observeSingleSelection(directionViewer);

		directionValues = new ControlValues(directionCombo);
		securityNameValues = new ControlValues(securityNameText).initTextObservable(SWT.FocusOut);
		isinValues = new ControlValues(isinText).initTextObservable(SWT.FocusOut);
		firstTradingDayValues = new ControlValues(firstTradingDayText).initTextObservable(SWT.FocusOut);
		lastTradingDayValues = new ControlValues(lastTradingDayText).initTextObservable(SWT.FocusOut);

		GridLayoutFactory.swtDefaults().numColumns(2).generateLayout(control);

		typeCombo.setFocus();
	}

	public Composite getControl() {
		return control;
	}

	public IControlValues getDirectionValues() {
		return directionValues;
	}

	public IControlValues getSecurityNameValues() {
		return securityNameValues;
	}

	public IControlValues getIsinValues() {
		return isinValues;
	}

	public IControlValues getFirstTradingDayValues() {
		return firstTradingDayValues;
	}

	public IControlValues getLastTradingDayValues() {
		return lastTradingDayValues;
	}

	public IViewerObservableValue getSecurityTypeObservable() {
		return securityTypeObservable;
	}

	public IViewerObservableValue getDirectionObservable() {
		return directionObservable;
	}

	public IObservableValue getDirectionComboEnablement() {
		return directionValues.getEnabledObservable();
	}
}
