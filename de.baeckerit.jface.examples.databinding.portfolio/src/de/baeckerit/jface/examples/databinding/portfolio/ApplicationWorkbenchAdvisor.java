package de.baeckerit.jface.examples.databinding.portfolio;

import java.text.Collator;

import org.eclipse.jface.util.Policy;
import org.eclipse.ui.application.IWorkbenchConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

public class ApplicationWorkbenchAdvisor extends WorkbenchAdvisor {

  private static final String PERSPECTIVE_ID = "de.baeckerit.jface.examples.databinding.portfolio.perspective";

  public WorkbenchWindowAdvisor createWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
    return new ApplicationWorkbenchWindowAdvisor(configurer);
  }

  public String getInitialWindowPerspectiveId() {
    return PERSPECTIVE_ID;
  }

  @Override
  public void initialize(IWorkbenchConfigurer configurer) {
    super.initialize(configurer);

    // Customize: Tell JFace to use the default locale's collator for
    // comparing Strings in class ViewerComparator.
    Policy.setComparator(Collator.getInstance());
  }
}
