package de.baeckerit.swt.builder.model;

import de.baeckerit.swt.builder.model.listener.MvcModelListener;

public class MvcController<M extends MvcModel> {

  protected M model;

  public MvcController() {
  }

  public MvcController(M model) {
    this.model = model;
  }

  public void addListener(MvcModelListener listener) {
    model.addListener(listener);
  }

  public void removeListener(MvcModelListener listener) {
    model.removeListener(listener);
  }

  public M getModel() {
    return model;
  }

  public void setModel(M model) {
    this.model = model;
  }
}
