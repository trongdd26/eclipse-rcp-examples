package de.baeckerit.jface.util;

import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ViewerCell;

import de.baeckerit.jdk.util.IProvidesDisplayName;

public class DisplayNameCellLabelProvider extends CellLabelProvider {
  public void update(ViewerCell cell) {
    cell.setText(((IProvidesDisplayName) cell.getElement()).getDisplayName());
  }
}