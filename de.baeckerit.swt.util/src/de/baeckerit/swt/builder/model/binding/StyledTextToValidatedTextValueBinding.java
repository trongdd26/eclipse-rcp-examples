package de.baeckerit.swt.builder.model.binding;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.graphics.Color;

import de.baeckerit.swt.builder.ControlBuilderBase;
import de.baeckerit.swt.builder.grid.BuilderCommon;
import de.baeckerit.swt.builder.model.command.ValidatedTextValueCommand;
import de.baeckerit.swt.builder.model.listener.ValidatedTextValueListener;
import de.baeckerit.swt.builder.model.text.ValidatedTextValue;

public class StyledTextToValidatedTextValueBinding {

  public static final Logger LOGGER = Logger.getLogger(StyledTextToValidatedTextValueBinding.class.getName());

  private boolean updating;
  private final Color foreground;

  public StyledTextToValidatedTextValueBinding(final ControlBuilderBase<?> builder, ValidatedTextValue value) {
    this(builder, value.getChangeValueCommand());
  }

  public StyledTextToValidatedTextValueBinding(final ControlBuilderBase<?> builder,
      final ValidatedTextValueCommand command) {
    final StyledText text = builder.createStyledText();
    foreground = text.getForeground();

    final ValidatedTextValue textValue = command.getTextValue();
    text.setTextLimit(textValue.getMaxLength());
    text.setText(textValue.getRawText());
    text.setEnabled(textValue.isEnabled());
    text.setVisible(textValue.isVisible());

    // Want to see exceptions here....
    computeStyles(text, textValue);

    command.getTextValue().addListener(new ValidatedTextValueListener() {
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
        callComputeStyles(text, textValue);
      }

      @Override
      public void valueChanged(String oldValue, String newValue) {
        if (!updating) {
          updating = true;
          try {
            text.setText(textValue.getValue());
          } catch (Throwable t) {
            LOGGER.log(Level.SEVERE, "Failed to update text control with validated text", t);
          } finally {
            updating = false;
          }
        }
        if (!textValue.isValidityChanged()) {
          callComputeStyles(text, textValue);
        }
      }

      @Override
      public void rawTextChanged(String oldValue, String newValue) {
        if (!updating && !textValue.isValueModified()) {
          updating = true;
          try {
            text.setText(textValue.getRawText());
          } catch (Throwable t) {
            LOGGER.log(Level.SEVERE, "Failed to update text control with raw text", t);
          } finally {
            updating = false;
          }
        }
        if (!textValue.isValidityChanged() && !textValue.isValueModified()) {
          callComputeStyles(text, textValue);
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
            if (!command.execute()) {
              // Nothing has changed, so we have to care for correct styles here.
              callComputeStyles(text, textValue);
            }
          } catch (Throwable t) {
            LOGGER.log(Level.SEVERE, "Failed to update text value", t);
          } finally {
            updating = false;
          }
        }
      }
    });

    // Workaround to Eclipse Bug #72225
    // Make StyledText to respect the text limit, if one is specified
    text.addVerifyListener(new VerifyListener() {
      @Override
      public void verifyText(VerifyEvent e) {
        int textLimit = text.getTextLimit();
        if (textLimit > 0) {
          int newLength = text.getCharCount() - (e.end - e.start) + e.text.length();
          if (newLength > textLimit) {
            e.doit = false;
          }
        }
      }
    });
  }

  private void callComputeStyles(StyledText text, ValidatedTextValue textValue) {
    try {
      computeStyles(text, textValue);
    } catch (Throwable t) {
      LOGGER.log(Level.SEVERE, "Failed to update styles", t);
    }
  }

  protected void computeStyles(final StyledText text, final ValidatedTextValue textValue) {
    StyleRange range = new StyleRange();
    range.start = 0;
    range.length = textValue.getRawText().length();
    range.foreground = foreground;
    text.setStyleRange(range);

    if (!textValue.isValid()) {
      int[] pos = textValue.computeInvalidPositions();
      for (int i = 0; i < pos.length; i++) {
        range = new StyleRange();
        range.start = pos[i];
        range.length = 1;
        range.foreground = BuilderCommon.INVALID_PROPERTY_COLOR;
        text.setStyleRange(range);
      }
    }
  }
}
