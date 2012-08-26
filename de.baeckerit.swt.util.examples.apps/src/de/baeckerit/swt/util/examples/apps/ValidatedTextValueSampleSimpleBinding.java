package de.baeckerit.swt.util.examples.apps;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import de.baeckerit.jdk.util.SetOfCharactersUtils;
import de.baeckerit.jdk.util.mvc.MvcModel;
import de.baeckerit.jdk.util.mvc.ValidatedTextValue;
import de.baeckerit.jdk.util.mvc.ValidatedTextValueCommand;
import de.baeckerit.swt.builder.grid.Builder;
import de.baeckerit.swt.builder.model.binding.StyledTextToValidatedTextValueBinding;
import de.baeckerit.swt.builder.model.binding.TextToValidatedTextValueBinding;

public class ValidatedTextValueSampleSimpleBinding {

  public static void main(String[] args) {

    Model model = new Model();

    Display display = new Display();
    Shell shell = new Shell(display);
    shell.setText(ValidatedTextValueSampleSimpleBinding.class.getSimpleName());

    Builder builder = new Builder(shell);

    String titleText = "Simple binding of a Text control to a TextValue";
    Builder nameGroupBuilder = builder.fillGrabH().createGroup(2, titleText);
    nameGroupBuilder.createLabel("Name:");

    ValidatedTextValueCommand command = new ValidatedTextValueCommand(model.getName());
    new TextToValidatedTextValueBinding(nameGroupBuilder.fillGrabH(), command);

    titleText = "Simple binding of a StyledText control to a TextValue";
    Builder styledNameGroupBuilder = builder.fillGrabH().createGroup(2, titleText);
    styledNameGroupBuilder.createLabel("Name:");

    new StyledTextToValidatedTextValueBinding(styledNameGroupBuilder.fillGrabH(), model.getName());

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
    private final ValidatedTextValue name;

    public Model() {
      name = new ValidatedTextValue(this, "Name", 10, SetOfCharactersUtils.ANY_LETTER);
    }

    public ValidatedTextValue getName() {
      return name;
    }
  }
}
