package de.baeckerit.jface.examples.databinding.portfolio.view;

import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ViewerCell;

import de.baeckerit.jface.examples.databinding.portfolio.data.ISecurityPosition;

public class SecurityPositionBuyCellLabelProvider extends CellLabelProvider {
  public void update(ViewerCell cell) {
    ISecurityPosition position = (ISecurityPosition) cell.getElement();
    cell.setText(position.isBuy() ? "Buy" : "Sell");
  }
}