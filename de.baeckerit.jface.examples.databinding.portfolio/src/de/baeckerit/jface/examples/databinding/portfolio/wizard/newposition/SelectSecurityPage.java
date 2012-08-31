package de.baeckerit.jface.examples.databinding.portfolio.wizard.newposition;

import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import de.baeckerit.jface.databinding.util.DefaultDataBindingContext;
import de.baeckerit.jface.databinding.util.ValidationMessageProviderWithDefault;
import de.baeckerit.jface.databinding.util.WizardPageSupport;
import de.baeckerit.jface.examples.databinding.portfolio.ServiceLocator;
import de.baeckerit.jface.examples.databinding.portfolio.data.Security;

public class SelectSecurityPage extends WizardPage {

  protected SelectSecurityPage() {
    super("SelectSecurityPage", "Select Security", getPageImage());
  }

  private static ImageDescriptor getPageImage() {
    Image image = new Image(Display.getDefault(), 16, 16);
    return ImageDescriptor.createFromImage(image);
  }

  public void createControl(Composite parent) {
    List<Security> viewableSecurities = ServiceLocator.getDataAccess().getSecurities();

    final NewPositionWizardModel model = ((NewPositionWizard) getWizard()).getModel();
    final SelectSecurityPageView view = new SelectSecurityPageView(parent, viewableSecurities);

    final DefaultDataBindingContext dbc = new DefaultDataBindingContext();
    dbc.bindValue(view.getSelectedSecurity(), model.getSelectedSecurity());

    WizardPageSupport support = new WizardPageSupport(this, dbc, model.getSelectionPageComplete());
    support.setValidationMessageProvider(new ValidationMessageProviderWithDefault("Select a Security for the new Position."));

    setControl(view.getControl());
  }
}