package de.baeckerit.jface.util;

import org.eclipse.jface.viewers.LabelProvider;

import de.baeckerit.jdk.util.IProvidesDisplayName;

public class DisplayNameLabelProvider extends LabelProvider {

  public DisplayNameLabelProvider() {
  }

  @Override
  public String getText(Object element) {
    return ((IProvidesDisplayName) element).getDisplayName();
  }
}
