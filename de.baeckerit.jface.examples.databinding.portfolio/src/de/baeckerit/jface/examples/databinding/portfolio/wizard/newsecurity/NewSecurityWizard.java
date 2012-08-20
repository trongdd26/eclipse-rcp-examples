package de.baeckerit.jface.examples.databinding.portfolio.wizard.newsecurity;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

import de.baeckerit.jface.examples.databinding.portfolio.EventHandling;
import de.baeckerit.jface.examples.databinding.portfolio.ServiceLocator;
import de.baeckerit.jface.examples.databinding.portfolio.access.IDataAccess;
import de.baeckerit.jface.examples.databinding.portfolio.viewable.ViewableSecurity;

public class NewSecurityWizard extends Wizard implements INewWizard {

	private final NewSecurityWizardModel model = new NewSecurityWizardModel();

	public NewSecurityWizardModel getModel() {
		return model;
	}

	@Override
	public boolean performFinish() {
		IDataAccess dataAccess = ServiceLocator.getDataAccess();
		ViewableSecurity security = dataAccess.addSecurity(model.getParams());
		if (security != null) {
			EventHandling.postNewSecurityEvent(security);
		}
		return security != null;
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
