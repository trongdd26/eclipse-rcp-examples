package de.baeckerit.jface.util;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;

import de.baeckerit.jdk.util.foco.IFoCo;

public class FoCoViewerComparator extends ViewerComparator {

  private final IFoCo[] focos;

  public FoCoViewerComparator(IFoCo... focos) {
    this.focos = focos.clone();
  }

  @Override
  public int compare(Viewer viewer, Object e1, Object e2) {
    int cmp = 0;
    for (int i = 0; cmp == 0 && i < focos.length; i++) {
      cmp = focos[i].compare(e1, e2);
    }
    return cmp;
  }
}
