package de.baeckerit.jface.util;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;

import de.baeckerit.jdk.util.IGetter;

public class ViewerSorterWithGetter<V extends Comparable<V>, T> extends ViewerSorter {
    private final IGetter<V, T> getter;

    public ViewerSorterWithGetter(IGetter<V, T> getter) {
        this.getter = getter;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public int compare(Viewer viewer, Object e1, Object e2) {
        return getter.get((T)e1).compareTo(getter.get((T)e2));
    }
}
