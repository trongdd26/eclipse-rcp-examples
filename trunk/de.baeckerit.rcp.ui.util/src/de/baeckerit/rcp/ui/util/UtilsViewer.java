package de.baeckerit.rcp.ui.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Menu;

import de.baeckerit.jdk.util.UtilsArray;
import de.baeckerit.rcp.ui.util.Adapters;

public class UtilsViewer {

  public static final Object[] getElements(Object inputElement) {
    if (inputElement == null)
      return UtilsArray.NO_OBJECTS;
    if (inputElement.getClass().isArray() && !inputElement.getClass().getComponentType().isPrimitive())
      return (Object[]) inputElement;
    if (inputElement instanceof Collection)
      return ((Collection<?>) inputElement).toArray();
    return new Object[] { inputElement };
  }

  public static final Object getFirstSelected(SelectionChangedEvent event) {
    return getFirstSelected(event.getSelection());
  }

  public static final Object getFirstSelected(ISelection selection) {
    if (selection instanceof StructuredSelection) {
      StructuredSelection structuredSelection = (StructuredSelection) selection;
      if (structuredSelection.size() == 1)
        return structuredSelection.getFirstElement();
    }
    return null;
  }

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

  public static MenuManager createContextMenu(Viewer viewer) {
    MenuManager menuMgr = new MenuManager("#PopupMenu"); //$NON-NLS-1$
    Menu menu = menuMgr.createContextMenu(viewer.getControl());
    menuMgr.add(new Separator("additions"));
    viewer.getControl().setMenu(menu);
    return menuMgr;
  }
}
