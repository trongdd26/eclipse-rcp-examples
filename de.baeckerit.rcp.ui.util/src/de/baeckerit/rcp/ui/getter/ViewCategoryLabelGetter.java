package de.baeckerit.rcp.ui.getter;

import org.eclipse.ui.views.IViewCategory;

import de.baeckerit.jdk.util.foco.AbstractStringFoCo;

public class ViewCategoryLabelGetter extends AbstractStringFoCo {

  public static final ViewCategoryLabelGetter INSTANCE = new ViewCategoryLabelGetter();

  @Override
  public String get(Object object) {
    return ((IViewCategory) object).getLabel();
  }
}
