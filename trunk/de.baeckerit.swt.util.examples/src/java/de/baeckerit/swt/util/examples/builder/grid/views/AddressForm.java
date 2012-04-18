package de.baeckerit.swt.util.examples.builder.grid.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import de.baeckerit.swt.builder.grid.BuilderCommon;
import de.baeckerit.swt.util.examples.ExampleView;


public class AddressForm extends ExampleView {

    @Override
    public void createPartControl(Composite parent) {
        BuilderCommon builder = new BuilderCommon(parent, 2).minWidth(400);
        
        builder.withLabel("First Name:").fillGrabH().createText();
        builder.withLabel("Last Name:").fillGrabH().createText();
        builder.withLabel("Birthday").addStyle(SWT.DROP_DOWN).createDateTime();

        builder.createLabel("Phone:");
        BuilderCommon phoneRow = builder.createGrabbingRow(6);
        phoneRow.withLabel("Office:").fillGrabH().createText();
        phoneRow.withLabel("Home:").fillGrabH().createText();
        phoneRow.withLabel("Mobile:").fillGrabH().createText();
        
        builder.vAlignTop().createLabel("Remarks:");
        builder.fillGrab().createTextArea();
    }
}
