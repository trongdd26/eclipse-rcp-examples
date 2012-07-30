package de.baeckerit.rcp.ui.util;

import org.eclipse.core.runtime.IAdaptable;

import de.baeckerit.jdk.util.IProvidesImageKey;
import de.baeckerit.jdk.util.IProvidesLabel;
import de.baeckerit.jdk.util.UtilsArray;

/**
 * @author Andreas Baecker
 * 
 * @param <NODE_OBJECT>
 * @param <PARENT>
 */
public abstract class EasyTreeNode<PARENT, NODE_OBJECT> implements IProvidesLabel, IProvidesImageKey, IAdaptable {

  private final PARENT parent;
  private final NODE_OBJECT nodeObject;
  private Object[] children;

  protected EasyTreeNode(PARENT parent, NODE_OBJECT nodeObject) {
    this.parent = parent;
    this.nodeObject = nodeObject;
    this.children = null;
  }

  public PARENT getParent() {
    return parent;
  }

  public NODE_OBJECT getNodeObject() {
    return nodeObject;
  }

  public boolean hasChildren() {
    return children == null ? true : children.length > 0;
  }

  public Object[] getChildren() {
    if (children == null) {
      children = computeChildren();
    }
    return children;
  }

  protected Object[] computeChildren() {
    return UtilsArray.NO_OBJECTS;
  }

  public String getLabel() {
    if (nodeObject instanceof IProvidesLabel)
      return ((IProvidesLabel) nodeObject).getLabel();
    return nodeObject.toString();
  }

  public String getImageKey() {
    if (nodeObject instanceof IProvidesImageKey) {
      IProvidesImageKey providesImageKey = (IProvidesImageKey) nodeObject;
      String imageKey = providesImageKey.getImageKey();
      if (imageKey != null)
        return imageKey;
    }
    // avoids dependency on org.eclipse.ui
    return "IMG_OBJ_FOLDER";
  }

  @SuppressWarnings("unchecked")
  @Override
  public Object getAdapter(@SuppressWarnings("rawtypes") Class adapterClass) {
    Object nodeAdapter = Adapters.getAdapter(nodeObject, adapterClass, true);
    return nodeAdapter != null ? nodeAdapter : Adapters.getAdapter(parent, adapterClass, true);
  }
}
