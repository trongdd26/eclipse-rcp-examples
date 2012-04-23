package de.baeckerit.swt.builder.model.binding;

import java.util.logging.Logger;

import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;

import de.baeckerit.jdk.util.ErrorLogger;
import de.baeckerit.jdk.util.mvc.MvcCommand;
import de.baeckerit.jdk.util.mvc.MvcCommandManager;
import de.baeckerit.jdk.util.mvc.ValidatedTextValue;
import de.baeckerit.jdk.util.mvc.ValidatedTextValueCommand;
import de.baeckerit.jdk.util.mvc.ValidatedTextValueListener;
import de.baeckerit.swt.builder.grid.BuilderCommonBase;

public class TextToValidatedTextValueBinding {

  public static final Logger LOGGER = Logger.getLogger(TextToValidatedTextValueBinding.class.getName());

  private final Text text;
  private boolean updating;

  public TextToValidatedTextValueBinding(final BuilderCommonBase<?> builder, final ValidatedTextValueCommand command) {
    text = builder.createText();

    final ValidatedTextValue textValue = command.getTextValue();
    text.setTextLimit(textValue.getMaxLength());
    text.setText(textValue.getValue());
    text.setEnabled(textValue.isEnabled());
    text.setVisible(textValue.isVisible());
    // text.setBackground(textValue.isValid() ? null : BuilderCommonBase.INVALID_PROPERTY_COLOR);

    final ValidatedTextValueListener textValueListener = new ValidatedTextValueListener() {
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
        // text.setBackground(valid ? null : BuilderCommonBase.INVALID_PROPERTY_COLOR);
      }

      @Override
      public boolean focusRequested() {
        return text.setFocus();
      }

      @Override
      public void valueChanged(String oldValue, String newValue) {
        if (!updating) {
          updating = true;
          try {
            text.setText(newValue);
          } catch (Throwable t) {
            String message = "Failed to update text control with raw text";
            ErrorLogger.logError(TextToValidatedTextValueBinding.class, message, t);
          } finally {
            updating = false;
          }
        }
      }

      @Override
      public void rawTextChanged(String oldValue, String newValue) {
        if (!updating && !textValue.isValueModified()) {
          updating = true;
          try {
            text.setText(textValue.getRawText());
          } catch (Throwable t) {
            String message = "Failed to update text control with raw text";
            ErrorLogger.logError(TextToValidatedTextValueBinding.class, message, t);
          } finally {
            updating = false;
          }
        }
      }
    };
    command.getTextValue().addListener(textValueListener);

    text.addDisposeListener(new DisposeListener() {
      @Override
      public void widgetDisposed(DisposeEvent event) {
        command.getTextValue().removeListener(textValueListener);
      }
    });

    text.addFocusListener(new FocusAdapter() {
      @Override
      public void focusGained(FocusEvent event) {
        text.selectAll();
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
            String message = "Failed to modify text value";
            ErrorLogger.logError(TextToValidatedTextValueBinding.class, message, t);
          } finally {
            updating = false;
          }
        }
      }
    });
  }
  public TextToValidatedTextValueBinding(BuilderCommonBase<?> builder, ValidatedTextValueCommand command, final MvcCommand selectionCommand,
      final boolean closeActiveShellOnSuccess) {
    this(builder, command);

    text.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetDefaultSelected(SelectionEvent e) {
        if (MvcCommandManager.execute(selectionCommand) && closeActiveShellOnSuccess) {
          Display.getDefault().getActiveShell().dispose();
        }
      }
    });
  }
}
