package de.baeckerit.jface.examples.databinding.portfolio.wizard.newsecurity;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

import de.baeckerit.jface.examples.databinding.portfolio.EventHandling;
import de.baeckerit.jface.examples.databinding.portfolio.ServiceLocator;
import de.baeckerit.jface.examples.databinding.portfolio.access.IDataAccess;
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
    ISecurity overlapping = dataAccess.addSecurity(security);
    if (overlapping == null) {
      EventHandling.postNewSecurityEvent(security);
    } else {
      MessageDialog.openError(getShell(), "Fehler", "Overlapping trading intervals!");
    }
    return overlapping == null;
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
