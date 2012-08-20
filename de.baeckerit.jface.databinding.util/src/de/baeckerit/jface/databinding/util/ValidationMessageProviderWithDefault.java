package de.baeckerit.jface.databinding.util;

import org.eclipse.core.databinding.ValidationStatusProvider;
import org.eclipse.jface.databinding.dialog.ValidationMessageProvider;

/**
 * Provides a default message if the {@link ValidationStatusProvider} doesn't
 * provide one.
 */
public final class ValidationMessageProviderWithDefault extends ValidationMessageProvider {

  private final String defaultMessage;

  public ValidationMessageProviderWithDefault(String defaultMessage) {
    this.defaultMessage = defaultMessage;
  }

  @Override
  public String getMessage(ValidationStatusProvider statusProvider) {
    String message = super.getMessage(statusProvider);
    return message == null ? defaultMessage : message;
  }
}