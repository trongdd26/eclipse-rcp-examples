package de.baeckerit.jface.util;

import org.eclipse.jface.viewers.ColumnLabelProvider;

import de.baeckerit.jdk.util.foco.IFoCo;

public class FoCoColumnLabelProvider extends ColumnLabelProvider {

  private final IFoCo foco;

  public FoCoColumnLabelProvider(IFoCo foco) {
    this.foco = foco;
  }

  public String getText(Object element) {
    return foco.format(element);
  }
}