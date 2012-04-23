package de.baeckerit.jdk.util.mvc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import de.baeckerit.jdk.util.ErrorLogger;

public abstract class SelectFromList<T> extends MvcProperty<SelectFromListListener<T>> {

  protected final List<T> elementsInternal;
  protected final List<T> selectedInternal;
  protected final List<T> modifiedInternal;
  private final List<T> elements;
  private final List<T> selected;
  private final List<T> modified;
  protected boolean elementsChanged;
  protected boolean selectedChanged;
  protected boolean modifiedChanged;

  public SelectFromList(MvcModel owner, String description) {
    super(owner, description);
    elementsInternal = new ArrayList<T>();
    selectedInternal = new ArrayList<T>();
    modifiedInternal = new ArrayList<T>();
    elements = Collections.unmodifiableList(elementsInternal);
    selected = Collections.unmodifiableList(selectedInternal);
    modified = Collections.unmodifiableList(modifiedInternal);

  }

  public List<T> getElements() {
    return elements;
  }

  public List<T> getSelected() {
    return selected;
  }

  public List<T> getModified() {
    return modified;
  }

  public boolean isElementsChanged() {
    return elementsChanged;
  }

  public boolean isSelectedChanged() {
    return selectedChanged;
  }

  public boolean isModifiedChanged() {
    return modifiedChanged;
  }

  public SelectFromListCommand<T> getChangeSelectedCommand() {
    return new SelectFromListCommand<T>(this);
  }

  public void setElements(Collection<? extends T> newElements) {
    elementsInternal.clear();
    elementsInternal.addAll(newElements);
    elementsChanged = true;

    if (internalResetSelection()) {
      selectedChanged = true;
    }

    modifiedInternal.clear();
    modifiedChanged = false;
  }

  public void setElementsAndNotify(Collection<? extends T> newElements) {
    setElements(newElements);
    getOwner().sendNotifications();
  }

  public boolean setSelected(List<T> newSelected) {
    validateNewSelected(newSelected);
    if (newSelected.isEmpty()) {
      if (selectedInternal.isEmpty())
        return false;
      else {
        selectedInternal.clear();
        selectedInternal.addAll(newSelected);
        selectedChanged = true;
        return true;
      }
    } else {
      final List<T> mappedElements = new ArrayList<T>(newSelected.size());
      for (T t : newSelected) {
        int index = elementsInternal.indexOf(t);
        if (index == -1)
          throw new NoSuchElementException();
        mappedElements.add(t);
      }
      if (mappedElements.size() == selectedInternal.size()) {
        boolean changed = false;
        for (int i = 0; i < mappedElements.size(); i++) {
          if (mappedElements.get(i) != selectedInternal.get(i)) {
            changed = true;
            break;
          }
        }
        if (!changed)
          return false;
      }
      selectedInternal.clear();
      selectedInternal.addAll(mappedElements);
      selectedChanged = true;
      return true;
    }
  }

  public boolean setSelectedAndNotify(List<T> newSelected) {
    boolean result = setSelected(newSelected);
    getOwner().sendNotifications();
    return result;
  }

  public boolean markAllElementsAsModified() {
    if (isEmpty())
      return false;
    modifiedInternal.clear();
    modifiedChanged = true;
    return true;
  }

  public boolean markAllElementsAsModifiedAndNotify() {
    boolean result = markAllElementsAsModified();
    getOwner().sendNotifications();
    return result;
  }

  @Override
  public boolean isEmpty() {
    return selectedInternal.isEmpty();
  }

  @Override
  public String getIsEmptyMessage() {
    return "Im Auswahlfeld \"" + getDescription() + "\" muss mindestens ein Element ausgewählt sein!";
  }

  @Override
  protected void checkpoint() {
    super.checkpoint();
    elementsChanged = false;
    selectedChanged = false;
    modifiedChanged = false;
    modifiedInternal.clear();
  }

  @Override
  public boolean isChanged() {
    return elementsChanged || selectedChanged || modifiedChanged || super.isChanged();
  }

  @Override
  protected void notifyListeners() {

    if (modifiedChanged && elementsChanged)
      throw new IllegalStateException("Currently not supported");

    super.notifyListeners();

    if (elementsChanged) {
      for (SelectFromListListener<T> listener : listeners) {
        try {
          listener.listChanged(elements);
        } catch (Throwable t) {
          ErrorLogger.logListener(SelectFromList.class, t);
        }
      }
    }
    if (modifiedChanged) {
      for (SelectFromListListener<T> listener : listeners) {
        try {
          listener.stateChanged(modified);
        } catch (Throwable t) {
          ErrorLogger.logListener(SelectFromList.class, t);
        }
      }
    }
    if (selectedChanged) {
      for (SelectFromListListener<T> listener : listeners) {
        try {
          listener.selectionChanged(selected);
        } catch (Throwable t) {
          ErrorLogger.logListener(SelectFromList.class, t);
        }
      }
    }
  }

  protected boolean internalResetSelection() {
    if (selectedInternal.isEmpty())
      return false;
    else {
      selectedInternal.clear();
      return true;
    }
  }

  protected void validateNewSelected(List<T> newSelected) {
    if (newSelected.size() > elementsInternal.size())
      throw new SelectionPolicyException();
  }
}
