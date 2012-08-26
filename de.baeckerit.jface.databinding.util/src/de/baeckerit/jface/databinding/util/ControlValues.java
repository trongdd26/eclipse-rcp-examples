package de.baeckerit.jface.databinding.util;

import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.widgets.Control;

public class ControlValues implements IControlValues {

  private final Control control;
  private ISWTObservableValue textObservable;
  private ISWTObservableValue focusObservable;
  private ISWTObservableValue enabledObservable;
  private ISWTObservableValue imageObservable;

  public ControlValues(Control control) {
    this.control = control;
  }

  public ISWTObservableValue getTextObservable() {
    if (textObservable == null) {
      textObservable = SWTObservables.observeText(control);
    }
    return textObservable;
  }

  public ISWTObservableValue getFocusObservable() {
    if (focusObservable == null) {
      focusObservable = new FixBug386870Property().observe(control);
      // focusObservable = SWTObservables.observeFocus(control);
    }
    return focusObservable;
  }

  public ISWTObservableValue getEnabledObservable() {
    if (enabledObservable == null) {
      enabledObservable = SWTObservables.observeEnabled(control);
    }
    return enabledObservable;
  }

  @Override
  public ISWTObservableValue getImageObservable() {
    if (imageObservable == null) {
      imageObservable = SWTObservables.observeImage(control);
    }
    return imageObservable;
  }

  public ControlValues initTextObservable(int event) {
    textObservable = SWTObservables.observeText(control, event);
    return this;
  }
}
