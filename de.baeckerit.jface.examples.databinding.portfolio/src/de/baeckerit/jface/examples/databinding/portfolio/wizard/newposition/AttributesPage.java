package de.baeckerit.jface.examples.databinding.portfolio.wizard.newposition;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import de.baeckerit.jface.databinding.util.DefaultDataBindingContext;
import de.baeckerit.jface.databinding.util.ValidationMessageProviderWithDefault;
import de.baeckerit.jface.databinding.util.WizardPageSupport;
import de.baeckerit.jface.databinding.util.binding.DateValueBindingFactory;

public class AttributesPage extends WizardPage {

  protected AttributesPage() {
    super("AttributesPage", "Security Position Attributes", getPageImage());
  }

  private static ImageDescriptor getPageImage() {
    Image image = new Image(Display.getDefault(), 16, 16);
    return ImageDescriptor.createFromImage(image);
  }

  public void createControl(Composite parent) {
    final NewPositionWizardModel model = ((NewPositionWizard) getWizard()).getModel();
    final AttributesPageView view = new AttributesPageView(parent);

    final DefaultDataBindingContext dbc = new DefaultDataBindingContext();
    dbc.bindValue(view.getActionValue(), model.getAction());
    dbc.bindValue(view.getOpenDateValues(), model.getOpenDate(), DateValueBindingFactory.FACTORY);

    WizardPageSupport support = new WizardPageSupport(this, dbc, model.getAttributesPageComplete());
    support.setValidationMessageProvider(new ValidationMessageProviderWithDefault("Specify mandatory properties of the new Position."));

    setControl(view.getControl());
  }
}