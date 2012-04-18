package de.baeckerit.jface.util;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;

public class JFACE {
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
}
