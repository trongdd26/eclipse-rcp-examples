package de.baeckerit.samples.rcp.view.multiple;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.views.IViewCategory;

import de.baeckerit.jface.util.JFACE;
import de.baeckerit.jface.util.SingleColumnTableLabelProviderWithGetters;
import de.baeckerit.jface.util.ViewerComparatorWithGetter;
import de.baeckerit.rcp.ui.getter.ViewCategoryImageGetter;
import de.baeckerit.rcp.ui.getter.ViewCategoryLabelGetter;
import de.baeckerit.rcp.ui.util.UI;
import de.baeckerit.rcp.ui.view.ViewPartWithViewer;

public class ViewCategories extends ViewPartWithViewer<TableViewer> {

  public void createPartControl(Composite parent) {
    viewer = new TableViewer(parent);
    viewer.setContentProvider(ArrayContentProvider.getInstance());
    ViewCategoryLabelGetter labelGetter = ViewCategoryLabelGetter.INSTANCE;
    ViewCategoryImageGetter imageGetter = ViewCategoryImageGetter.INSTANCE;
    viewer.setLabelProvider(new SingleColumnTableLabelProviderWithGetters<>(labelGetter, imageGetter));
    viewer.setComparator(new ViewerComparatorWithGetter<>(labelGetter));
    viewer.setInput(UI.getViewRegistry().getCategories());

    viewer.addDoubleClickListener(new IDoubleClickListener() {

      @Override
      public void doubleClick(DoubleClickEvent event) {
        IViewCategory selected = JFACE.getFirstElement(IViewCategory.class, event);
        try {
          UI.getActivePage().showView(ViewDescriptorsByCategory.ID, selected.getId(), IWorkbenchPage.VIEW_ACTIVATE);
        } catch (PartInitException e) {
          e.printStackTrace();
        }
      }
    });
  }
}
