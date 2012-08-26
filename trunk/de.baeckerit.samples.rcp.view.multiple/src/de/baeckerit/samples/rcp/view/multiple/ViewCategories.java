package de.baeckerit.samples.rcp.view.multiple;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.views.IViewCategory;

import de.baeckerit.jface.util.JFaceUtils;
import de.baeckerit.rcp.ui.util.UI;
import de.baeckerit.rcp.ui.view.ViewPartWithViewer;

public class ViewCategories extends ViewPartWithViewer<TableViewer> {

  public void createPartControl(Composite parent) {
    viewer = new TableViewer(parent);
    viewer.setContentProvider(ArrayContentProvider.getInstance());
    viewer.setLabelProvider(new LabelProvider() {
      @Override
      public String getText(Object element) {
        return ((IViewCategory) element).getLabel();
      }
    });
    viewer.setComparator(new ViewerComparator());
    viewer.setInput(UI.getViewRegistry().getCategories());

    viewer.addDoubleClickListener(new IDoubleClickListener() {

      @Override
      public void doubleClick(DoubleClickEvent event) {
        IViewCategory selected = JFaceUtils.getFirstElement(IViewCategory.class, event);
        try {
          UI.getActivePage().showView(ViewDescriptorsByCategory.ID, selected.getId(), IWorkbenchPage.VIEW_ACTIVATE);
        } catch (PartInitException e) {
          e.printStackTrace();
        }
      }
    });
  }
}
