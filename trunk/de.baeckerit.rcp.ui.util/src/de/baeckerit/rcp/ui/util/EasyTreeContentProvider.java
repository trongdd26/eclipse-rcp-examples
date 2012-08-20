package de.baeckerit.rcp.ui.util;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;

import de.baeckerit.jface.util.JFaceUtils;


public class EasyTreeContentProvider implements ITreeContentProvider {

  protected TreeViewer treeViewer;

  @Override
  public void dispose() {
    treeViewer = null;
  }

  @Override
  public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
    treeViewer = (TreeViewer) viewer;
  }

  @Override
  public Object[] getElements(Object inputElement) {
    return JFaceUtils.getElements(inputElement);
  }

  @Override
  public Object[] getChildren(Object parentElement) {
    if (parentElement instanceof EasyTreeNode)
      return ((EasyTreeNode<?, ?>) parentElement).getChildren();
    return null;
  }

  @Override
  public Object getParent(Object element) {
    if (element instanceof EasyTreeNode)
      return ((EasyTreeNode<?, ?>) element).getParent();
    return null;
  }

  @Override
  public boolean hasChildren(Object element) {
    if (element instanceof EasyTreeNode)
      return ((EasyTreeNode<?, ?>) element).hasChildren();
    return false;
  }
}
