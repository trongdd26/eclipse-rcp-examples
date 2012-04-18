package de.baeckerit.swt.builder.model.select;

import java.util.List;

import de.baeckerit.swt.builder.model.MvcModel;
import de.baeckerit.swt.builder.model.exception.SelectionPolicyException;

public class SelectAllwaysOne<T> extends SelectFromList<T> {

  public SelectAllwaysOne(MvcModel owner, String description) {
    super(owner, description);
  }

  @Override
  protected boolean internalResetSelection() {
    if (elementsInternal.isEmpty())
      return super.internalResetSelection();
    else {
      T newSelected = elementsInternal.get(0);
      boolean changed = selectedInternal.isEmpty() || newSelected != selectedInternal.get(0);
      selectedInternal.add(newSelected);
      return changed;
    }
  }

  @Override
  protected void validateNewSelected(List<T> newSelected) {
    super.validateNewSelected(newSelected);
    if (newSelected.size() > 1)
      throw new SelectionPolicyException();
    if (newSelected.isEmpty() && !elementsInternal.isEmpty())
      throw new SelectionPolicyException();
  }
}
