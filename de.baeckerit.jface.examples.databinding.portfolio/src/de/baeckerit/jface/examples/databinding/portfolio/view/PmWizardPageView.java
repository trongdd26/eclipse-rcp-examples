package de.baeckerit.jface.examples.databinding.portfolio.view;

import org.eclipse.swt.widgets.Composite;

public class PmWizardPageView {

  private final Composite parent;

  public PmWizardPageView(Composite parent) {
    this.parent = parent;
  }

  public Composite getParent() {
    return parent;
  }
}
