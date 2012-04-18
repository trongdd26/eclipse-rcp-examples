package de.baeckerit.swt.builder;

import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Composite;

import de.baeckerit.swt.builder.grid.BuilderCommonBase;

public abstract class ControlBuilderBase<T extends ControlBuilderBase<T>> extends BuilderCommonBase<T> {

  public ControlBuilderBase() {
    super();
  }

  public ControlBuilderBase(Composite parent) {
    super(parent);
  }

  protected ControlBuilderBase(ControlBuilderBase<T> other) {
    super(other);
  }

  public ControlBuilderBase(Composite parent, int numColumns) {
    super(parent, numColumns);
  }

  public StyledText createStyledText() {
    final int style = computeStyle(getDefaultStyles().getTextStyle());
    return initializeControl(new StyledText(getComposite(), style));
  }
}
