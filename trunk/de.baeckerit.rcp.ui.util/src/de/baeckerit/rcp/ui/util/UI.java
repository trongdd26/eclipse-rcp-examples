package de.baeckerit.rcp.ui.util;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.registry.ViewRegistry;
import org.eclipse.ui.views.IViewCategory;
import org.eclipse.ui.views.IViewRegistry;

@SuppressWarnings("restriction")
public class UI {

    public static IViewCategory findViewCategory(String id) {
        return ((ViewRegistry) PlatformUI.getWorkbench().getViewRegistry()).findCategory(id);
    }
    
    public static IWorkbenchPage getActivePage() {
        return PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
    }
    
    public static IViewRegistry getViewRegistry() {
        return PlatformUI.getWorkbench().getViewRegistry();
    }
    
    public static ISharedImages getSharedImages() {
        return PlatformUI.getWorkbench().getSharedImages();
    }
    
    public static Image getSharedImage(String symbolicName) {
        return getSharedImages().getImage(symbolicName);
    }
    
    public static ImageDescriptor getSharedImageDescriptor(String symbolicName) {
        return getSharedImages().getImageDescriptor(symbolicName);
    }

    public static Image safeDispose(final Image image) {
      if (image != null && !image.isDisposed())
        image.dispose();
      return null;
    }
}
