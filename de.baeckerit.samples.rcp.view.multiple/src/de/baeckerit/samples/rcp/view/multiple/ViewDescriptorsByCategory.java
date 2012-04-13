package de.baeckerit.samples.rcp.view.multiple;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.views.IViewCategory;
import org.eclipse.ui.views.IViewDescriptor;
import org.eclipse.ui.views.IViewRegistry;

import de.baeckerit.rcp.ui.util.UI;

public class ViewDescriptorsByCategory extends ViewPart {

    public static final String ID = "de.baeckerit.samples.rcp.view.viewDescriptorsByCategory";

    private TableViewer viewer;

    public void createPartControl(Composite parent) {
        viewer = new TableViewer(parent);
        viewer.setContentProvider(ArrayContentProvider.getInstance());
        viewer.setLabelProvider(new ViewLabelProvider());
        viewer.setSorter(new ViewDescriptorSorter());

        String secondaryId = getViewSite().getSecondaryId();
        IViewRegistry viewRegistry = PlatformUI.getWorkbench().getViewRegistry();
        if (secondaryId == null) {
            viewer.setInput(viewRegistry.getViews());
            setPartName("Views - ALL");
        } else {
            IViewCategory viewCategory = UI.findViewCategory(secondaryId);
            viewer.setInput(viewCategory.getViews());
            setPartName("Views - " + viewCategory.getLabel());
        }
    }

    class ViewLabelProvider extends LabelProvider implements ITableLabelProvider {
        private ImageRegistry images = new ImageRegistry();

        public String getColumnText(Object obj, int index) {
            return ((IViewDescriptor) obj).getLabel();
        }

        public Image getColumnImage(Object obj, int index) {
            IViewDescriptor viewDescriptor = (IViewDescriptor) obj;
            Image image = images.get(viewDescriptor.getId());
            if (image == null) {
                ImageDescriptor imageDescriptor = viewDescriptor.getImageDescriptor();
                if (imageDescriptor != null) {
                    image = (Image) imageDescriptor.createImage(false, Display.getCurrent());
                    if (image != null) {
                        images.put(viewDescriptor.getId(), image);
                    }
                }
            }
            return image;
        }

        public void dispose() {
            images.dispose();
            super.dispose();
        }
    }

    class ViewDescriptorSorter extends ViewerSorter {
        public int compare(Viewer viewer, Object e1, Object e2) {
            return ((IViewDescriptor) e1).getLabel().compareTo(((IViewDescriptor) e2).getLabel());
        }
    }

    public void setFocus() {
        viewer.getControl().setFocus();
    }
}
