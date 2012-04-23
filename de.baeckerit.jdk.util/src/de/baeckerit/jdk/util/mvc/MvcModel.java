package de.baeckerit.jdk.util.mvc;

import java.util.ArrayList;
import java.util.List;

import de.baeckerit.jdk.util.ErrorLogger;
import de.baeckerit.jdk.util.UtilsArray;

public class MvcModel {

  // TODO ABA provide base class for listeners: support enabledment, self update
  // TODO ABA provide factories for services: error logging, ???? 
  // TODO ABA support edit/view mode
  // TODO ABA provide list of changed properties

  private final List<MvcProperty<?>> properties;
  private final List<MvcModelListener> listeners;

  public MvcModel() {
    this.properties = new ArrayList<MvcProperty<?>>();
    this.listeners = new ArrayList<MvcModelListener>();
  }

  public void addListener(MvcModelListener listener) {
    // TODO ABA lock while notifying. synchronized???
    listeners.add(listener);
  }

  public void removeListener(MvcModelListener listener) {
    // TODO ABA lock while notifying. synchronized???
    listeners.remove(listener);
  }

  public void sendNotifications() {
    sendNotifications(false);
  }

  /**
   * Notifies listeners of the model and its properties. Resets the modified flags of all modified
   * model properties.
   * 
   * @param fakeModelChanged if true, model listeners are notified even when none of the model's
   *        properties has actually changed its state.
   */
  public void sendNotifications(boolean fakeModelChanged) {
    boolean modified = false;
    for (MvcProperty<?> property : properties)
      if (property.isChanged()) {
        modified = true;
        try {
          property.notifyListeners();
        } catch (Throwable t) {
          // TODO ABA extract constant
          ErrorLogger.logError(MvcModel.class, "Error notifying listeners of property", t);
        }
      }

    if (modified || fakeModelChanged) {
      MvcModelListener[] copy = UtilsArray.toArray(listeners, MvcModelListener.class);
      for (MvcModelListener listener : copy) {
        try {
          listener.modelModified();
        } catch (Throwable t) {
          ErrorLogger.logListener(MvcModel.class, t);
        }
      }

      for (MvcProperty<?> property : properties)
        if (property.isChanged()) {
          try {
            property.checkpoint();
          } catch (Throwable t) {
            // TODO ABA extract constant
            ErrorLogger.logError(MvcModel.class, "Failed to checkpoint property", t);
          }
        }
    }
  }

  void add(MvcProperty<?> property) {
    properties.add(property);
  }
}
