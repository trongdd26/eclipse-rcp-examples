package de.baeckerit.jface.util;

import org.eclipse.jface.viewers.ViewerComparator;

import de.baeckerit.jdk.util.IProvidesDisplayName;

public class DisplayNameViewerComparator extends ViewerComparator {
  public int compare(org.eclipse.jface.viewers.Viewer viewer, Object e1, Object e2) {
    return getComparator().compare(((IProvidesDisplayName) e1).getDisplayName(), ((IProvidesDisplayName) e2).getDisplayName());
  }
}