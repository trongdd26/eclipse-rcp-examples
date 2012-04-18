package de.baeckerit.swt.builder.model.internal;

import de.baeckerit.swt.builder.model.MvcUtils;

public class TrackedValue<T> {
  private final T defaultValue;
  private T currentValue;
  private T checkpointedValue;

  public TrackedValue(T defaultValue, T initialValue) {
    this.defaultValue = defaultValue;
    this.currentValue = initialValue;
    this.checkpointedValue = initialValue;
  }

  public T getDefaultValue() {
    return defaultValue;
  }

  public T getCurrentValue() {
    return currentValue;
  }

  public T getCheckpointedValue() {
    return checkpointedValue;
  }

  public boolean setCurrentValue(T currentValue) {
    boolean different = !MvcUtils.equals(this.currentValue, currentValue);
    this.currentValue = currentValue;
    return different;
  }

  public boolean isChanged() {
    return !MvcUtils.equals(checkpointedValue, currentValue);
  }

  public void checkpoint() {
    checkpointedValue = currentValue;
  }

  public void reset() {
    currentValue = defaultValue;
  }

  public void rollback() {
    currentValue = checkpointedValue;
  }
}
