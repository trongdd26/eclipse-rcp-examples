package de.baeckerit.swt.util;

import org.eclipse.swt.graphics.Image;

import de.baeckerit.jdk.util.getter.IGetter;

public interface IImageGetter<T> extends IGetter<Image, T> {
  Image get(T object);
}
