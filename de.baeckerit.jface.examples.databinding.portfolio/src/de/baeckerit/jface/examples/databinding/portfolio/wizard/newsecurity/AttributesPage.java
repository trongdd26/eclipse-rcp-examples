package de.baeckerit.jface.examples.databinding.portfolio.wizard.newsecurity;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import de.baeckerit.jface.databinding.util.DefaultDataBindingContext;
import de.baeckerit.jface.databinding.util.ValidationMessageProviderWithDefault;
import de.baeckerit.jface.databinding.util.WizardPageSupport;
import de.baeckerit.jface.databinding.util.binding.DateValueBindingFactory;
import de.baeckerit.jface.databinding.util.binding.IsinValueBindingFactory;
import de.baeckerit.jface.databinding.util.binding.TextValueBindingFactory;

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
    dbc.bindValue(view.getSecurityNameValues(), model.getSecurityName(), TextValueBindingFactory.FACTORY);
    dbc.bindValue(view.getIsinValues(), model.getIsin(), IsinValueBindingFactory.FACTORY);
    dbc.bindValue(view.getFirstTradingDayValues(), model.getFirstTradingDay(), DateValueBindingFactory.FACTORY);
    dbc.bindValue(view.getLastTradingDayValues(), model.getLastTradingDay(), DateValueBindingFactory.FACTORY);
    dbc.bindValue(view.getDirectionComboEnablement(), model.getDirectionEnabled());

    dbc.addValidationStatusProvider(model.getTradingRangeValidator());

    WizardPageSupport support = new WizardPageSupport(this, dbc, model.getComplete());
    support.setValidationMessageProvider(new ValidationMessageProviderWithDefault("Specify mandatory properties of the new Security."));

    setControl(view.getControl());
  }
}