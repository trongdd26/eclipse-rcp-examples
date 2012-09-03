package de.baeckerit.jface.examples.databinding.portfolio.wizard.newposition;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

import de.baeckerit.jface.examples.databinding.portfolio.EventHandling;
import de.baeckerit.jface.examples.databinding.portfolio.ServiceLocator;
import de.baeckerit.jface.examples.databinding.portfolio.access.IDataAccess;
import de.baeckerit.jface.examples.databinding.portfolio.data.ISecurityPosition;

public class NewPositionWizard extends Wizard implements INewWizard {

  private final NewPositionWizardModel model = new NewPositionWizardModel();
  private final AttributesPage attributesPage;
  private final SelectSecurityPage selectSecurityPage;

  public NewPositionWizard() {
    setNeedsProgressMonitor(true);
    selectSecurityPage = new SelectSecurityPage();
    attributesPage = new AttributesPage();
  }

  public NewPositionWizardModel getModel() {
    return model;
  }

  @Override
  public boolean performFinish() {
    final IDataAccess dataAccess = ServiceLocator.getDataAccess();
    final ISecurityPosition newSecurityPosition = dataAccess.createSecurityPosition();
    model.fillParams(newSecurityPosition);
    boolean success = dataAccess.addSecurityPosition(newSecurityPosition);
    if (success) {
      EventHandling.postNewSecurityPositionEvent(newSecurityPosition);
    }
    return success;
  }

  @Override
  public void addPages() {
    addPage(selectSecurityPage);
    addPage(attributesPage);
  }

  @Override
  public void init(IWorkbench workbench, IStructuredSelection selection) {
  }

  public String getWindowTitle() {
    return "Create a new Position in a Security";
  }
}
