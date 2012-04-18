package de.baeckerit.swt.util.examples.apps;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import de.baeckerit.swt.builder.grid.Builder;
import de.baeckerit.swt.builder.model.MvcModel;
import de.baeckerit.swt.builder.model.binding.StyledTextToTextValueBinding;
import de.baeckerit.swt.builder.model.binding.TextToTextValueBinding;
import de.baeckerit.swt.builder.model.command.TextValueCommand;
import de.baeckerit.swt.builder.model.text.TextValue;

public class TextValueSampleSimpleBinding {

  public static void main(String[] args) {

    Model model = new Model();

    Display display = new Display();
    Shell shell = new Shell(display);
    shell.setText(TextValueSampleSimpleBinding.class.getSimpleName());

    Builder builder = new Builder(shell);

    String titleText = "Simple binding of a Text control to a TextValue";
    Builder nameGroupBuilder = builder.fillGrabH().createGroup(2, titleText);
    nameGroupBuilder.createLabel("Name:");

    new TextToTextValueBinding(nameGroupBuilder.fillGrabH(), new TextValueCommand(model.getName()));

    titleText = "Simple binding of a StyledText control to a TextValue";
    Builder styledNameGroupBuilder = builder.fillGrabH().createGroup(2, titleText);
    styledNameGroupBuilder.createLabel("Name:");

    new StyledTextToTextValueBinding(styledNameGroupBuilder.fillGrabH(), model.getName());

    shell.pack();
    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
    display.dispose();
  }

  static class Model extends MvcModel {
    private final TextValue name;

    public Model() {
      name = new TextValue(this, "Name", 10);
    }

    public TextValue getName() {
      return name;
    }
  }
}
