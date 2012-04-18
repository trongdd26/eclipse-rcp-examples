package de.baeckerit.rcp.ui.util;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

import de.baeckerit.jdk.util.ErrorLogger;

public class EasyTableLabelProvider implements ITableLabelProvider {

  @Override
  public final String getColumnText(Object element, int columnIndex) {
    try {
      return safeGetColumnText(element, columnIndex);
    } catch (Throwable t) {
      ErrorLogger.logError(EasyTableLabelProvider.class, "Could not get text for column " + columnIndex, t);
      return "Error!";
    }
  }

  public String safeGetColumnText(Object element, int columnIndex) {
    return "";
  }

  @Override
  public final Image getColumnImage(Object element, int columnIndex) {
    try {
      return safeGetColumnImage(element, columnIndex);
    } catch (Throwable t) {
      ErrorLogger.logError(EasyTableLabelProvider.class, "Could not get image for column " + columnIndex, t);
      return null;
    }
  }

  public Image safeGetColumnImage(Object element, int columnIndex) {
    return null;
  }

  @Override
  public void addListener(ILabelProviderListener listener) {
  }

  @Override
  public void dispose() {
  }

  @Override
  public boolean isLabelProperty(Object element, String property) {
    return true;
  }

  @Override
  public void removeListener(ILabelProviderListener listener) {
  }
}