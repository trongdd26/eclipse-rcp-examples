package de.baeckerit.jdk.util.mvc;

import java.util.List;


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
