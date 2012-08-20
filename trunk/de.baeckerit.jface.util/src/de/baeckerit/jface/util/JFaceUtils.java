package de.baeckerit.jface.util;

import java.util.Collection;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.TableColumn;

import de.baeckerit.jdk.util.UtilsArray;
import de.baeckerit.jdk.util.getter.Getter;

public class JFaceUtils {
  public static <T> T getFirstElement(Class<T> clazz, ISelection selection) {
    if (selection instanceof IStructuredSelection) {
      IStructuredSelection structuredSelection = (IStructuredSelection) selection;
      Object firstElement = structuredSelection.getFirstElement();
      if (clazz.isInstance(firstElement)) {
        return clazz.cast(firstElement);
      }
    }
    return null;
  }

  public static <T> T getFirstElement(Class<T> clazz, DoubleClickEvent event) {
    return getFirstElement(clazz, event.getSelection());
  }

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
    return getFirstElement(Object.class, selection);
  }

  public static MenuManager createContextMenu(Viewer viewer) {
    MenuManager menuMgr = new MenuManager("#PopupMenu"); //$NON-NLS-1$
    Menu menu = menuMgr.createContextMenu(viewer.getControl());
    menuMgr.add(new Separator("additions"));
    viewer.getControl().setMenu(menu);
    return menuMgr;
  }

  public static TableViewerColumn createColumn(TableViewer viewer, String title, int width) {
    final TableViewerColumn viewerColumn = new TableViewerColumn(viewer, SWT.NONE);
    final TableColumn column = viewerColumn.getColumn();
    column.setText(title);
    column.setWidth(width);
    return viewerColumn;
  }

  public static TableViewerColumn createColumn(TableViewer viewer, String title, int width, CellLabelProvider labelProvider) {
    final TableViewerColumn viewerColumn = createColumn(viewer, title, width);
    viewerColumn.setLabelProvider(labelProvider);
    return viewerColumn;
  }

  public static TableViewerColumn createColumn(TableViewer viewer, String title, int width, Getter getter) {
    return createColumn(viewer, title, width, new GetterColumnLabelProvider(getter));
  }
}
