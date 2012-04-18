package de.baeckerit.swt.builder.model.select;

import java.util.List;

import de.baeckerit.swt.builder.model.MvcModel;
import de.baeckerit.swt.builder.model.exception.SelectionPolicyException;

public class SelectOne<T> extends SelectFromList<T> {

  public SelectOne(MvcModel owner, String description) {
    super(owner, description);
  }

  @Override
  protected void validateNewSelected(List<T> newSelected) {
    super.validateNewSelected(newSelected);
    if (newSelected.size() > 1)
      throw new SelectionPolicyException();
  }
}
