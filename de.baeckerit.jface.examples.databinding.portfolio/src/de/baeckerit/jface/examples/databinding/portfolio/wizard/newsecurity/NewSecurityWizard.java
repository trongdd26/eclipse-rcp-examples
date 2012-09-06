package de.baeckerit.jface.examples.databinding.portfolio.wizard.newsecurity;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

import de.baeckerit.jface.examples.databinding.portfolio.EventHandling;
import de.baeckerit.jface.examples.databinding.portfolio.ServiceLocator;
import de.baeckerit.jface.examples.databinding.portfolio.access.IDataAccess;
import de.baeckerit.jface.examples.databinding.portfolio.access.IDataAccess.AddSecurityResult;
import de.baeckerit.jface.examples.databinding.portfolio.data.ISecurity;

public class NewSecurityWizard extends Wizard implements INewWizard {

  private final NewSecurityWizardModel model = new NewSecurityWizardModel();

  public NewSecurityWizardModel getModel() {
    return model;
  }

  @Override
  public boolean performFinish() {
    final IDataAccess dataAccess = ServiceLocator.getDataAccess();
    final ISecurity security = dataAccess.createSecurity();
    model.fillParams(security);
    AddSecurityResult result = dataAccess.addSecurity(security);
    if (result == AddSecurityResult.OK) {
      EventHandling.postNewSecurityEvent(security);
    } else if (result == AddSecurityResult.OVERLAPPING) {
      MessageDialog.openError(getShell(), "Error saving new security", "Overlapping trading intervals!");
    } else {
      MessageDialog.openError(getShell(), "Error saving new security", "Unknown error! See log file for details!");
    }
    return result == AddSecurityResult.OK;
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
