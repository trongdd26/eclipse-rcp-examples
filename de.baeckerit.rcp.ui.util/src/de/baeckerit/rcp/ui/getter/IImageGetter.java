package de.baeckerit.rcp.ui.getter;

import org.eclipse.swt.graphics.Image;

public interface IImageGetter<T> extends IGetter<Image, T> {
    Image get(T object);
}
