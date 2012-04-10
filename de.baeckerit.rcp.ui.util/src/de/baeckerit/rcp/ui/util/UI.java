package de.baeckerit.rcp.ui.util;

import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.registry.ViewRegistry;
import org.eclipse.ui.views.IViewCategory;

@SuppressWarnings("restriction")
public class UI {

    public static IViewCategory findViewCategory(String id) {
        return ((ViewRegistry) PlatformUI.getWorkbench().getViewRegistry()).findCategory(id);
    }
}
