package de.baeckerit.rcp.ui.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;


public class UtilsViewer {

  public static final <T> List<T> getSelectedElements(ISelection selection, Class<T> clazz) {
    if (selection instanceof StructuredSelection) {
      StructuredSelection structuredSelection = (StructuredSelection) selection;
      if (structuredSelection.isEmpty())
        return Collections.emptyList();
      Object[] selected = structuredSelection.toArray();
      List<T> elements = new ArrayList<T>();
      for (int i = 0; i < selected.length; i++) {
        T element = Adapters.getAdapter(selected[i], clazz, true);
        if (element != null) {
          elements.add(element);
        }
      }
      return elements;
    }
    return Collections.emptyList();
  }

  @SuppressWarnings("unchecked")
  public static final <T> List<T> getSelectedElements(ISelection selection) {
    return (List<T>) getSelectedElements(selection, Object.class);
  }
}
