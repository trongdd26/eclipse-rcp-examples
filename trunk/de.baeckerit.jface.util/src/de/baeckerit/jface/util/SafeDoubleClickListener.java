package de.baeckerit.jface.util;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;

import de.baeckerit.jdk.util.ErrorLogger;

public abstract class SafeDoubleClickListener implements IDoubleClickListener {

  @Override
  public void doubleClick(DoubleClickEvent event) {
    try {
      safeDoubleClick(event);
    } catch (Throwable t) {
      ErrorLogger.logListener(SafeDoubleClickListener.class, t);
    }
  }

  public abstract void safeDoubleClick(DoubleClickEvent event);
}
