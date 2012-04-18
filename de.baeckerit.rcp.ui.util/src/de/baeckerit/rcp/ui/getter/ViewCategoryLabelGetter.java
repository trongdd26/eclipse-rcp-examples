package de.baeckerit.rcp.ui.getter;

import org.eclipse.ui.views.IViewCategory;

import de.baeckerit.jdk.util.IStringGetter;

public class ViewCategoryLabelGetter implements IStringGetter<IViewCategory> {
    
    public static final ViewCategoryLabelGetter INSTANCE = new ViewCategoryLabelGetter();
    
    @Override
    public String get(IViewCategory viewCategory) {
        return viewCategory.getLabel();
    }
}
