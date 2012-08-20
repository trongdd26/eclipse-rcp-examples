package de.baeckerit.jface.util;

import org.eclipse.jface.viewers.LabelProvider;

import de.baeckerit.jdk.util.IProvidesDisplayName;

public class DisplayNameLabelProvider extends LabelProvider {

  public DisplayNameLabelProvider() {
  }

  @Override
  public String getText(Object element) {
    if (element instanceof IProvidesDisplayName) {
      IProvidesDisplayName displayable = (IProvidesDisplayName) element;
      String displayName = displayable.getDisplayName();
      if (displayName != null && !displayName.trim().isEmpty()) {
        return displayName;
      }
    }
    return super.getText(element);
  }
}
