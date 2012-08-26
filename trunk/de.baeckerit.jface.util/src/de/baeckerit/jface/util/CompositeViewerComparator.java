package de.baeckerit.jface.util;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;

public class CompositeViewerComparator extends ViewerComparator {

  private final ViewerComparator[] comparators;

  public CompositeViewerComparator(ViewerComparator... comparators) {
    this.comparators = comparators.clone();
  }

  @Override
  public int compare(Viewer viewer, Object e1, Object e2) {
    int cmp = 0;
    for (int i = 0; cmp == 0 && i < comparators.length; i++) {
      cmp = comparators[i].compare(viewer, e1, e2);
    }
    return cmp;
  }
}
