package de.baeckerit.swt.util;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import de.baeckerit.jdk.util.errors.ErrorLogger;

public class SafeSelectionListener implements SelectionListener {

  @Override
  public final void widgetSelected(SelectionEvent e) {
    try {
      safeWidgetSelected(e);
    } catch (Throwable t) {
      ErrorLogger.logListener(SafeSelectionListener.class, t);
    }
  }

  @Override
  public final void widgetDefaultSelected(SelectionEvent e) {
    try {
      safeWidgetDefaultSelected(e);
    } catch (Throwable t) {
      ErrorLogger.logListener(SafeSelectionListener.class, t);
    }
  }

  public void safeWidgetSelected(SelectionEvent e) {
  }

  public void safeWidgetDefaultSelected(SelectionEvent e) {
  }
}
