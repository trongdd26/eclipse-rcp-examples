package de.baeckerit.jface.examples.databinding.portfolio.wizard.newsecurity;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import de.baeckerit.jface.databinding.util.DefaultDataBindingContext;
import de.baeckerit.jface.databinding.util.ValidationMessageProviderWithDefault;
import de.baeckerit.jface.databinding.util.WizardPageSupport;

public class AttributesPage extends WizardPage {

	protected AttributesPage() {
		super("AttributesPage", "Security Attributes", getPageImage());
	}

	private static ImageDescriptor getPageImage() {
		Image image = new Image(Display.getDefault(), 16, 16);
		return ImageDescriptor.createFromImage(image);
	}

	public void createControl(Composite parent) {
		final NewSecurityWizardModel model = ((NewSecurityWizard) getWizard()).getModel();
		final AttributesPageView view = new AttributesPageView(parent);

		final DefaultDataBindingContext dbc = new DefaultDataBindingContext();
		dbc.bindValue(view.getSecurityTypeObservable(), model.getSecurityType());
		dbc.bindValue(view.getDirectionObservable(), model.getSecurityDirection());
		dbc.bindTextValue(view.getSecurityNameValues(), model.getSecurityName());
		dbc.bindIsinValue(view.getIsinValues(), model.getIsin());
		dbc.bindDateValue(view.getFirstTradingDayValues(), model.getFirstTradingDay());
		dbc.bindDateValue(view.getLastTradingDayValues(), model.getLastTradingDay());
		dbc.bindValue(view.getDirectionComboEnablement(), model.getDirectionEnabled());

		dbc.addValidationStatusProvider(model.getTradingRangeValidator());

		WizardPageSupport support = new WizardPageSupport(this, dbc, model.getComplete());
		support.setValidationMessageProvider(new ValidationMessageProviderWithDefault(
				"Specify mandatory properties of the new Security."));

		setControl(view.getControl());
	}
}