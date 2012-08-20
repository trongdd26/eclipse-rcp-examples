package de.baeckerit.jface.examples.databinding.portfolio.wizard.newposition;

import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.SelectObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import de.baeckerit.jface.databinding.util.ControlValues;
import de.baeckerit.jface.databinding.util.IControlValues;
import de.baeckerit.jface.examples.databinding.portfolio.view.PmWizardPageView;

public class AttributesPageView extends PmWizardPageView {

	private final Composite control;
	private final SelectObservableValue actionValue;
	private final IControlValues openDateValues;

	public AttributesPageView(Composite parent) {
		super(parent);

		control = new Composite(parent, SWT.NONE);
		control.setLayout(GridLayoutFactory.swtDefaults().numColumns(2).create());

		Group actionGroup = new Group(control, SWT.NONE);
		actionGroup.setText("Action");
		actionGroup.setLayoutData(GridDataFactory.fillDefaults().span(2, 1).create());
		actionGroup.setLayout(new RowLayout());

		Button buyButton = new Button(actionGroup, SWT.RADIO);
		buyButton.setText("Buy");

		Button sellButton = new Button(actionGroup, SWT.RADIO);
		sellButton.setText("Sell");

		new Label(control, SWT.NONE).setText("Opened:");
		Text openDateText = new Text(control, SWT.BORDER);

		GridLayoutFactory.swtDefaults().numColumns(2).generateLayout(control);

		actionValue = new SelectObservableValue(Boolean.class);
		actionValue.addOption(Boolean.TRUE, SWTObservables.observeSelection(buyButton));
		actionValue.addOption(Boolean.FALSE, SWTObservables.observeSelection(sellButton));

		buyButton.setFocus();

		openDateValues = new ControlValues(openDateText).initTextObservable(SWT.FocusOut);
	}

	public Composite getControl() {
		return control;
	}

	public IObservableValue getActionValue() {
		return actionValue;
	}

	public IControlValues getOpenDateValues() {
		return openDateValues;
	}
}
