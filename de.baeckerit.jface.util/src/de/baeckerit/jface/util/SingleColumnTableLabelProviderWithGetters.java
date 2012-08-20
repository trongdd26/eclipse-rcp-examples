package de.baeckerit.jface.util;

import org.eclipse.swt.graphics.Image;

import de.baeckerit.jdk.util.foco.AbstractStringFoCo;
import de.baeckerit.jdk.util.getter.IGetter;

public class SingleColumnTableLabelProviderWithGetters<T> extends SingleColumnTableLabelProvider {

  private final AbstractStringFoCo labelGetter;
  private final IGetter<Image, T> imageGetter;

  public SingleColumnTableLabelProviderWithGetters(AbstractStringFoCo labelGetter) {
    this.labelGetter = labelGetter;
    this.imageGetter = null;
  }

  public SingleColumnTableLabelProviderWithGetters(AbstractStringFoCo labelGetter, IGetter<Image, T> imageGetter) {
    this.labelGetter = labelGetter;
    this.imageGetter = imageGetter;
  }

  @SuppressWarnings("unchecked")
  @Override
  public String getText(Object element) {
    return labelGetter.get((T) element);
  }

  @SuppressWarnings("unchecked")
  @Override
  public Image getImage(Object element) {
    return imageGetter == null ? null : imageGetter.get((T) element);
  }
}
