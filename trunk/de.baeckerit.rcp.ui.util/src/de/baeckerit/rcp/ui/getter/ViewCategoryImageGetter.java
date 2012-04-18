package de.baeckerit.rcp.ui.getter;

import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.views.IViewCategory;

import de.baeckerit.rcp.ui.util.UI;
import de.baeckerit.swt.util.IImageGetter;

public class ViewCategoryImageGetter implements IImageGetter<IViewCategory> {

    public static final ViewCategoryImageGetter INSTANCE = new ViewCategoryImageGetter();
    
    @Override
    public Image get(IViewCategory viewCategory) {
        return UI.getSharedImage(ISharedImages.IMG_OBJ_FOLDER);
    }
}
