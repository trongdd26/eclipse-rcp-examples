package de.baeckerit.rcp.ui.util;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import de.baeckerit.jface.util.JFaceUtils;


public class EasyContentProvider implements IStructuredContentProvider {

  @Override
  public void dispose() {
  }

  @Override
  public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
  }

  @Override
  public Object[] getElements(Object inputElement) {
    return JFaceUtils.getElements(inputElement);
  }
}
