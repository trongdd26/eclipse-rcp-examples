package de.baeckerit.jface.examples.databinding.portfolio.wizard.newposition;

import java.util.List;

import org.eclipse.jface.databinding.viewers.IViewerObservableValue;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

import de.baeckerit.jdk.util.foco.DisplayNameFoCo;
import de.baeckerit.jface.examples.databinding.portfolio.util.compare.ViewableSecurityViewerComparator;
import de.baeckerit.jface.examples.databinding.portfolio.view.PmWizardPageView;
import de.baeckerit.jface.examples.databinding.portfolio.viewable.ViewableSecurity;
import de.baeckerit.jface.util.JFaceUtils;
import de.baeckerit.swt.util.SWTUtils;

public class SelectSecurityPageView extends PmWizardPageView {

  private final Composite control;
  private final TableViewer viewer;

  private final IViewerObservableValue selectedSecurity;

  public SelectSecurityPageView(Composite parent, List<ViewableSecurity> viewableSecurities) {
    super(parent);

    control = new Composite(parent, SWT.NONE);
    control.setLayout(new FillLayout());

    viewer = new TableViewer(SWTUtils.createTable(control));
    JFaceUtils.createColumn(viewer, "ISIN", 100, ViewableSecurity.GET_ISIN);
    JFaceUtils.createColumn(viewer, "Name", 300, DisplayNameFoCo.INSTANCE);
    JFaceUtils.createColumn(viewer, "Type", 50, ViewableSecurity.GET_TYPE_NAME);
    JFaceUtils.createColumn(viewer, "Last Traded", 100, ViewableSecurity.GET_LAST_TRADING_DAY);

    viewer.setComparator(new ViewableSecurityViewerComparator());
    viewer.setContentProvider(new ArrayContentProvider());
    viewer.setInput(viewableSecurities);

    selectedSecurity = ViewersObservables.observeSingleSelection(viewer);

    viewer.getTable().setFocus();
  }

  public Composite getControl() {
    return control;
  }

  public IViewerObservableValue getSelectedSecurity() {
    return selectedSecurity;
  }
}
