package de.baeckerit.swt.builder.grid;

import org.eclipse.swt.widgets.Composite;

import de.baeckerit.swt.builder.ControlBuilderBase;

public class Builder extends ControlBuilderBase<Builder> {

  public Builder() {
    super();
  }

  public Builder(Composite parent) {
    super(parent);
  }

  public Builder(Composite parent, int numColumns) {
    super(parent, numColumns);
  }

  protected Builder(Builder other) {
    super(other);
  }

  @Override
  protected Builder copy() {
    return new Builder(this);
  }

  @Override
  protected Builder _this() {
    return this;
  }
}
