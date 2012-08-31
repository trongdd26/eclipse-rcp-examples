package de.baeckerit.jface.examples.databinding.portfolio.wizard.newsecurity;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

import de.baeckerit.jface.examples.databinding.portfolio.EventHandling;
import de.baeckerit.jface.examples.databinding.portfolio.ServiceLocator;
import de.baeckerit.jface.examples.databinding.portfolio.data.Security;

public class NewSecurityWizard extends Wizard implements INewWizard {

  private final NewSecurityWizardModel model = new NewSecurityWizardModel();

  public NewSecurityWizardModel getModel() {
    return model;
  }

  @Override
  public boolean performFinish() {
    Security security = model.getParams();
    boolean success = ServiceLocator.getDataAccess().addSecurity(security);
    if (success) {
      EventHandling.postNewSecurityEvent(security);
    }
    return success;
  }

  @Override
  public void addPages() {
    addPage(new AttributesPage());
  }

  @Override
  public void init(IWorkbench workbench, IStructuredSelection selection) {
  }

  public String getWindowTitle() {
    return "Create a new Security";
  }
}
