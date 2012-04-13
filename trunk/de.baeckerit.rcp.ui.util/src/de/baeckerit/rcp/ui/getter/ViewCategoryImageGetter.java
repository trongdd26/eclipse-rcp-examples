package de.baeckerit.rcp.ui.getter;

import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.views.IViewCategory;

import de.baeckerit.rcp.ui.util.UI;

public class ViewCategoryImageGetter implements IImageGetter<IViewCategory> {
    @Override
    public Image get(IViewCategory viewCategory) {
        return UI.getSharedImage(ISharedImages.IMG_OBJ_FOLDER);
    }
}
