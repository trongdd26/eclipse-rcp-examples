package de.baeckerit.swt.builder.grid;

import org.eclipse.swt.widgets.Composite;

public class BuilderCommon extends BuilderCommonBase<BuilderCommon> {

  public BuilderCommon() {
    super();
  }

  public BuilderCommon(Composite parent) {
    super(parent);
  }

  public BuilderCommon(Composite parent, int numColumns) {
    super(parent, numColumns);
  }

  protected BuilderCommon(BuilderCommon other) {
    super(other);
  }

  @Override
  protected BuilderCommon copy() {
    return new BuilderCommon(this);
  }

  @Override
  protected BuilderCommon _this() {
    return this;
  }
}
