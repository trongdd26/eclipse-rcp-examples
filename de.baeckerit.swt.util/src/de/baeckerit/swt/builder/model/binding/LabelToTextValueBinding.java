package de.baeckerit.swt.builder.model.binding;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Label;

import de.baeckerit.jdk.util.mvc.TextValue;
import de.baeckerit.jdk.util.mvc.TextValueListener;

public class LabelToTextValueBinding {

  public static final Logger LOGGER = Logger.getLogger(LabelToTextValueBinding.class.getName());

  public LabelToTextValueBinding(final Label label, final TextValue textValue) {

    final TextValueListener textValueListener = new TextValueListener() {

      @Override
      public void safeUpdateSelf() {
        super.safeUpdateSelf();
        label.setText(textValue.getValue());
        label.setEnabled(textValue.isEnabled());
        label.setVisible(textValue.isVisible());
      }

      @Override
      public void visibilityChanged(boolean visible) {
        label.setVisible(visible);
      }

      @Override
      public void enablementChanged(boolean enabled) {
        label.setEnabled(enabled);
      }

      @Override
      public void valueChanged(String oldValue, String newValue) {
        if (isEnabled()) {
          try {
            label.setText(newValue);
          } catch (Throwable t) {
            LOGGER.log(Level.SEVERE, "Failed to update label control", t);
          }
        }
      }
    };
    textValue.addListener(textValueListener);

    textValueListener.updateSelf();

    label.addDisposeListener(new DisposeListener() {
      @Override
      public void widgetDisposed(DisposeEvent event) {
        textValue.removeListener(textValueListener);
      }
    });
  }
}
