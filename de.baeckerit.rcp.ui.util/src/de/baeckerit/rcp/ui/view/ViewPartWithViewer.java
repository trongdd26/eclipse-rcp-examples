package de.baeckerit.rcp.ui.view;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.part.ViewPart;

public abstract class ViewPartWithViewer<VIEWER extends Viewer> extends ViewPart {

    protected VIEWER viewer;

    public void setFocus() {
        if (viewer != null && viewer.getControl() != null && !viewer.getControl().isDisposed()) {
            viewer.getControl().setFocus();
        }
    }
}
