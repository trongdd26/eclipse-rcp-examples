package de.baeckerit.jface.util;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;

import de.baeckerit.jdk.util.errors.ErrorLogger;

public abstract class SafeSelectionChangedListener implements ISelectionChangedListener {

  @Override
  public final void selectionChanged(SelectionChangedEvent event) {
    try {
      safeSelectionChanged(event);
    } catch (Throwable t) {
      ErrorLogger.logListener(SafeSelectionChangedListener.class, t);
    }
  }

  public abstract void safeSelectionChanged(SelectionChangedEvent event);
}
