package de.baeckerit.rcp.ui.util;

import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import de.baeckerit.jdk.util.ErrorLogger;
import de.baeckerit.jdk.util.IProvidesImageKey;
import de.baeckerit.jdk.util.IProvidesLabel;

public class EasyLabelProvider extends LabelProvider {

  @Override
  public final String getText(Object element) {
    try {
      return safeGetText(element);
    } catch (Throwable t) {
      ErrorLogger.logError(EasyLabelProvider.class, "Could not get text", t);
      return "Error!";
    }
  }

  public String safeGetText(Object element) {
    if (element instanceof IProvidesLabel)
      return ((IProvidesLabel) element).getLabel();
    return element.toString();
  }

  @Override
  public Image getImage(Object element) {
    try {
      return safeGetImage(element);
    } catch (Throwable t) {
      ErrorLogger.logError(EasyLabelProvider.class, "Could not get image", t);
      return null;
    }
  }

  public Image safeGetImage(Object element) {
    if (element instanceof IProvidesImageKey) {
      String imageKey = ((IProvidesImageKey) element).getImageKey();
      if (imageKey != null) {
        Image image = JFaceResources.getImage(imageKey);
        if (image == null) {
          ErrorLogger.logWarn(EasyLabelProvider.class, "Could not find JFace image {" + imageKey + "}");
        }
        return image;
      }
    }
    return null;
  }
}