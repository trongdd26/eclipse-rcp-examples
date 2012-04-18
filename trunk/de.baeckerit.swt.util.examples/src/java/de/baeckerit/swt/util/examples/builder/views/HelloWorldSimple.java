package de.baeckerit.swt.util.examples.builder.views;

import org.eclipse.swt.widgets.Composite;

import de.baeckerit.swt.builder.ControlBuilder;
import de.baeckerit.swt.util.examples.ExampleView;

public class HelloWorldSimple extends ExampleView {

  @Override
  public void createPartControl(Composite parent) {
    new ControlBuilder(parent).createLabel("Hello, World!");
  }
}
