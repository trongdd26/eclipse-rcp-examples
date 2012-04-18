package de.baeckerit.swt.util.examples.builder.grid.views;

import org.eclipse.swt.widgets.Composite;

import de.baeckerit.swt.builder.grid.BuilderCommon;
import de.baeckerit.swt.util.examples.ExampleView;


public class HelloWorldGrid extends ExampleView {

    @Override
    public void createPartControl(Composite parent) {
        new BuilderCommon(parent).createLabel("Hello, World!");
    }
}
