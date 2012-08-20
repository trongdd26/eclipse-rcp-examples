package de.baeckerit.jdk.util.mvc;

import de.baeckerit.jdk.util.errors.ErrorLogger;

public abstract class MvcPropertyListener {

  private boolean enabled = true;

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    if (this.enabled != enabled) {
      this.enabled = enabled;
      if (enabled) {
        updateSelf();
      }
    }
  }
  public void validityChanged(boolean valid) {
  }

  public void visibilityChanged(boolean visible) {
  }

  public void enablementChanged(boolean enabled) {
  }

  public boolean focusRequested() {
    return false;
  }

  public final void updateSelf() {
    if (isEnabled()) {
      try {
        safeUpdateSelf();
      } catch (Throwable t) {
        ErrorLogger.logListener(MvcPropertyListener.class, t);
      }
    }
  }

  protected void safeUpdateSelf() {
  }
}
