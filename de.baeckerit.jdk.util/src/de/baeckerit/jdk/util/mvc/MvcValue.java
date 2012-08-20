package de.baeckerit.jdk.util.mvc;

import de.baeckerit.jdk.util.errors.ErrorLogger;

public abstract class MvcValue<T, LT extends MvcValueListener<T>> extends MvcProperty<LT> {

  private final TrackedValue<T> value;

  public MvcValue(MvcModel owner, String description, T defaultValue, T initialValue) {
    super(owner, description);
    this.value = new TrackedValue<T>(defaultValue, initialValue);
  }

  public T getValue() {
    return value.getCurrentValue();
  }

  public T getDefaultValue() {
    return value.getDefaultValue();
  }

  public T getPreviousValue() {
    return value.getCheckpointedValue();
  }

  @Override
  public boolean isChanged() {
    return isValueModified() || super.isChanged();
  }

  public boolean isValueModified() {
    return value.isChanged();
  }

  public boolean setValue(T newValue) {
    return value.setCurrentValue(newValue);
  }

  public boolean setValueAndNotify(T newValue) {
    boolean changed = setValue(newValue);
    getOwner().sendNotifications();
    return changed;
  }

  public void rollbackValue() {
    value.rollback();
  }

  public void rollbackValueAndNotify() {
    rollbackValue();
    getOwner().sendNotifications();
  }

  public abstract MvcCommand getChangeValueCommand();

  @Override
  public void reset() {
    value.reset();
    super.reset();
  }

  @Override
  protected void checkpoint() {
    value.checkpoint();
    super.checkpoint();
  }

  @Override
  protected void notifyListeners() {
    // Must update the value first
    if (value.isChanged()) {
      for (LT listener : listeners) {
        try {
          listener.valueChanged(value.getCheckpointedValue(), value.getCurrentValue());
        } catch (Throwable t) {
          ErrorLogger.logListener(MvcValue.class, t);
        }
      }
    }

    super.notifyListeners();
  }
}
