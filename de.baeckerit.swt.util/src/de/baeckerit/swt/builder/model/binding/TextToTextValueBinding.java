package de.baeckerit.swt.builder.model.binding;

import java.util.logging.Level;
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

import de.baeckerit.swt.builder.grid.BuilderCommonBase;
import de.baeckerit.swt.builder.model.command.MvcCommand;
import de.baeckerit.swt.builder.model.command.MvcCommandManager;
import de.baeckerit.swt.builder.model.command.TextValueCommand;
import de.baeckerit.swt.builder.model.listener.TextValueListener;
import de.baeckerit.swt.builder.model.text.TextValue;

public class TextToTextValueBinding {

  // TODO ABA support enablement/visibility for aux labels/controls

  public static final Logger LOGGER = Logger.getLogger(TextToTextValueBinding.class.getName());

  private final Text text;
  private final TextValue textValue;
  private boolean updating;

  public TextToTextValueBinding(final BuilderCommonBase<?> builder, final TextValueCommand command) {
    text = builder.createText();
    textValue = command.getTextValue();

    final TextValueListener textValueListener = new TextValueListener() {

      @Override
      public void safeUpdateSelf() {
        super.safeUpdateSelf();
        text.setTextLimit(textValue.getMaxLength());
        text.setText(textValue.getValue());
        text.setEnabled(textValue.isEnabled());
        text.setVisible(textValue.isVisible());
        text.setBackground(textValue.isValid() ? null : BuilderCommonBase.INVALID_PROPERTY_COLOR);
      }

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
        text.setBackground(valid ? null : BuilderCommonBase.INVALID_PROPERTY_COLOR);
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
            LOGGER.log(Level.SEVERE, "Failed to update text control", t);
          } finally {
            updating = false;
          }
        }
      }
    };
    textValue.addListener(textValueListener);

    textValueListener.updateSelf();

    text.addDisposeListener(new DisposeListener() {
      @Override
      public void widgetDisposed(DisposeEvent event) {
        textValue.removeListener(textValueListener);
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
          // The widget might be disposed here. Why????
          try {
            if (!text.isDisposed()) {
              command.setNewValue(text.getText());
              command.execute();
            }
          } catch (Throwable t) {
            LOGGER.log(Level.SEVERE, "Failed to modify text value", t);
          } finally {
            updating = false;
          }
        }
      }
    });
  }

  public TextToTextValueBinding(final BuilderCommonBase<?> builder, final TextValueCommand command, final MvcCommand selectionCommand,
      boolean closeActiveShellOnSuccess) {
    this(builder, command);

    text.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetDefaultSelected(SelectionEvent e) {
        if (MvcCommandManager.execute(selectionCommand)) {
          Display.getDefault().getActiveShell().dispose();
        }
      }
    });
  }
}
