package de.baeckerit.swt.builder.model.binding;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;

import de.baeckerit.jdk.util.mvc.TextValue;
import de.baeckerit.jdk.util.mvc.TextValueCommand;
import de.baeckerit.jdk.util.mvc.TextValueListener;
import de.baeckerit.swt.builder.ControlBuilderBase;
import de.baeckerit.swt.builder.grid.BuilderCommon;

public class StyledTextToTextValueBinding {

  public static final Logger LOGGER = Logger.getLogger(StyledTextToTextValueBinding.class.getName());

  private boolean updating;

  public StyledTextToTextValueBinding(final ControlBuilderBase<?> builder, TextValue value) {
    this(builder, value.getChangeValueCommand());
  }

  public StyledTextToTextValueBinding(final ControlBuilderBase<?> builder, final TextValueCommand command) {
    final StyledText text = builder.createStyledText();

    final TextValue textValue = command.getTextValue();
    text.setTextLimit(textValue.getMaxLength());
    text.setText(textValue.getValue());
    text.setEnabled(textValue.isEnabled());
    text.setVisible(textValue.isVisible());
    text.setBackground(textValue.isValid() ? null : BuilderCommon.INVALID_PROPERTY_COLOR);

    command.getTextValue().addListener(new TextValueListener() {
      @Override
      public void visibilityChanged(boolean visible) {
        text.setVisible(visible);
      }

      @Override
      public void enablementChanged(boolean enabled) {
        text.setEnabled(enabled);
      }

      @Override
      public void validityChanged(boolean valid) {
        text.setBackground(valid ? null : BuilderCommon.INVALID_PROPERTY_COLOR);
      }

      @Override
      public void valueChanged(String oldValue, String newValue) {
        if (!updating) {
          updating = true;
          try {
            text.setText(newValue);
          } catch (Throwable t) {
            LOGGER.log(Level.SEVERE, "Failed to update text control", t);
          } finally {
            updating = false;
          }
        }
      }
    });

    text.addModifyListener(new ModifyListener() {
      @Override
      public void modifyText(ModifyEvent e) {
        if (!updating) {
          updating = true;
          try {
            command.setNewValue(text.getText());
            command.execute();
          } catch (Throwable t) {
            LOGGER.log(Level.SEVERE, "Failed to update text value", t);
          } finally {
            updating = false;
          }
        }
      }
    });
  }
}
