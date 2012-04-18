package de.baeckerit.swt.builder;

import org.eclipse.swt.widgets.Composite;

public class ControlBuilder extends ControlBuilderBase<ControlBuilder> {

  public ControlBuilder() {
    super();
  }

  public ControlBuilder(Composite parent) {
    super(parent);
  }

  protected ControlBuilder(ControlBuilder other) {
    super(other);
  }

  @Override
  protected ControlBuilder copy() {
    return new ControlBuilder(this);
  }

  @Override
  protected ControlBuilder _this() {
    return this;
  }
}
