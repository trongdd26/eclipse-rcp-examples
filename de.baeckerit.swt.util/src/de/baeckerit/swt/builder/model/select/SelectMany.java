package de.baeckerit.swt.builder.model.select;

import de.baeckerit.swt.builder.model.MvcModel;

public class SelectMany<T> extends SelectFromList<T> {

  public SelectMany(MvcModel owner, String description) {
    super(owner, description);
  }
}
