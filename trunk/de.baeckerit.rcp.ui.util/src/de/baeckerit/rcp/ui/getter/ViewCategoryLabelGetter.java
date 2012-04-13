package de.baeckerit.rcp.ui.getter;

import org.eclipse.ui.views.IViewCategory;

public class ViewCategoryLabelGetter implements IStringGetter<IViewCategory> {
    @Override
    public String get(IViewCategory viewCategory) {
        return viewCategory.getLabel();
    }
}
