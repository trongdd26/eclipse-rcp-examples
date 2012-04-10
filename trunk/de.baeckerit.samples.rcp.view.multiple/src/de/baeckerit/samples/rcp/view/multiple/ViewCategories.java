package de.baeckerit.samples.rcp.view.multiple;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.views.IViewCategory;

public class ViewCategories extends ViewPart {

    private TableViewer viewer;

    public void setFocus() {
        viewer.getControl().setFocus();
    }

    public void createPartControl(Composite parent) {
        viewer = new TableViewer(parent);
        viewer.setContentProvider(ArrayContentProvider.getInstance());
        viewer.setLabelProvider(new ViewCategoryLabelProvider());
        viewer.setSorter(new ViewCategorySorter());
        viewer.setInput(PlatformUI.getWorkbench().getViewRegistry().getCategories());

        viewer.addDoubleClickListener(new IDoubleClickListener() {

            @Override
            public void doubleClick(DoubleClickEvent event) {
                IStructuredSelection selection = (IStructuredSelection) event.getSelection();
                IViewCategory selected = (IViewCategory) selection.getFirstElement();
                IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
                try {
                    window.getActivePage().showView(ViewDescriptorsByCategory.ID, selected.getId(),
                                    IWorkbenchPage.VIEW_ACTIVATE);
                } catch (PartInitException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    class ViewCategoryLabelProvider extends LabelProvider implements ITableLabelProvider {
        public String getColumnText(Object obj, int index) {
            return ((IViewCategory) obj).getLabel();
        }

        public Image getColumnImage(Object obj, int index) {
            return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FOLDER);
        }
    }

    class ViewCategorySorter extends ViewerSorter {
        public int compare(Viewer viewer, Object e1, Object e2) {
            return ((IViewCategory) e1).getLabel().compareTo(((IViewCategory) e2).getLabel());
        }
    }
}
