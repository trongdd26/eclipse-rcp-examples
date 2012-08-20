package de.baeckerit.jdk.util.mvc;

import java.util.ArrayList;
import java.util.List;

import de.baeckerit.jdk.util.errors.ErrorLogger;

/**
 * TODO ABA add editable flag
 * 
 * @author Andreas
 * 
 * @param <LT>
 */
public abstract class MvcProperty<LT extends MvcPropertyListener> {

  public static final MvcProperty<?>[] NO_PROPERTIES = new MvcProperty<?>[0];

  private final MvcModel owner;
  private final String description;
  private final TrackedValue<Boolean> valid;
  private final TrackedValue<Boolean> visible;
  private final TrackedValue<Boolean> enabled;
  protected final List<LT> listeners;

  public MvcProperty(MvcModel owner, String description) {
    this.owner = owner;
    this.description = description;
    this.valid = new TrackedValue<Boolean>(true, true);
    this.visible = new TrackedValue<Boolean>(true, true);
    this.enabled = new TrackedValue<Boolean>(true, true);
    this.listeners = new ArrayList<LT>();

    checkInvariant();
    owner.add(this);
  }

  public MvcModel getOwner() {
    return owner;
  }

  public String getDescription() {
    return description;
  }

  public String getLabel() {
    return description + ":";
  }

  public boolean isEmpty() {
    return false;
  }

  public abstract String getIsEmptyMessage();

  public String getIsNotValidMessage() {
    return "Das Feld \"" + getDescription() + "\" enthält eine ungültige Eingabe!";
  }

  public boolean isValid() {
    return valid.getCurrentValue();
  }

  protected boolean setValid(boolean valid) {
    return this.valid.setCurrentValue(valid);
  }

  public boolean isVisible() {
    return visible.getCurrentValue();
  }

  public boolean setVisible(boolean visible) {
    return this.visible.setCurrentValue(visible);
  }

  public void setVisibleAndNotify(boolean visible) {
    setVisible(visible);
    getOwner().sendNotifications();
  }

  public boolean isEnabled() {
    return enabled.getCurrentValue();
  }

  public boolean setEnabled(boolean enabled) {
    return this.enabled.setCurrentValue(enabled);
  }

  public void setEnabledAndNotify(boolean enabled) {
    setEnabled(enabled);
    getOwner().sendNotifications();
  }

  public boolean isChanged() {
    return isValidityChanged() || isVisibilityChanged() || isEnablementChanged();
  }

  public boolean isValidityChanged() {
    return valid.isChanged();
  }

  public boolean isVisibilityChanged() {
    return visible.isChanged();
  }

  public boolean isEnablementChanged() {
    return enabled.isChanged();
  }

  public void addListener(LT listener) {
    listeners.add(listener);
  }

  public void addListenerAndNotify(LT listener) {
    listeners.add(listener);
    // TODO ABA notify the listener
  }

  public void removeListener(LT listener) {
    listeners.remove(listener);
  }

  protected void notifyListeners() {
    notifyValidityChanged();
    notifyVisibilityChanged();
    notifyEnablementChanged();
  }

  protected void checkpoint() {
    valid.checkpoint();
    visible.checkpoint();
    enabled.checkpoint();
  }

  public void reset() {
    valid.reset();
    visible.reset();
    enabled.reset();
  }

  public void resetAndNotify() {
    reset();
    getOwner().sendNotifications();
  }

  public boolean requestFocus() {
    for (MvcPropertyListener listener : listeners) {
      try {
        if (listener.focusRequested())
          return true;
      } catch (Throwable t) {
        ErrorLogger.logListener(MvcProperty.class, t);
      }
    }
    return false;
  }

  private void notifyValidityChanged() {
    if (valid.isChanged()) {
      for (MvcPropertyListener listener : listeners) {
        try {
          listener.validityChanged(valid.getCurrentValue());
        } catch (Throwable t) {
          ErrorLogger.logListener(MvcProperty.class, t);
        }
      }
    }
  }

  private void notifyVisibilityChanged() {
    if (visible.isChanged()) {
      for (MvcPropertyListener listener : listeners) {
        try {
          listener.visibilityChanged(visible.getCurrentValue());
        } catch (Throwable t) {
          ErrorLogger.logListener(MvcProperty.class, t);
        }
      }
    }
  }

  private void notifyEnablementChanged() {
    if (enabled.isChanged()) {
      for (MvcPropertyListener listener : listeners) {
        try {
          listener.enablementChanged(enabled.getCurrentValue());
        } catch (Throwable t) {
          ErrorLogger.logListener(MvcProperty.class, t);
        }
      }
    }
  }

  private void checkInvariant() {
    if (owner == null)
      throw new NullPointerException("owner");
    if (description == null)
      throw new NullPointerException("description");
  }
}
