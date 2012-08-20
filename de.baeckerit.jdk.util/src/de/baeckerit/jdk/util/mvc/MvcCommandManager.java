package de.baeckerit.jdk.util.mvc;

import de.baeckerit.jdk.util.errors.ErrorLogger;
import de.baeckerit.jdk.util.messages.MessageDisplay;

public class MvcCommandManager {

  public static boolean execute(MvcCommand command) {
    try {
      MvcProperty<?>[] requiredProperties = command.getRequiredProperties();
      for (MvcProperty<?> mvcProperty : requiredProperties) {
        if (mvcProperty.isEmpty()) {
          MessageDisplay.error(mvcProperty.getIsEmptyMessage());
          mvcProperty.requestFocus();
          return false;
        }
      }
      for (MvcProperty<?> mvcProperty : requiredProperties) {
        if (!mvcProperty.isValid()) {
          MessageDisplay.error(mvcProperty.getIsNotValidMessage());
          mvcProperty.requestFocus();
          return false;
        }
      }
      MvcProperty<?>[] optionalProperties = command.getOptionalProperties();
      for (MvcProperty<?> mvcProperty : optionalProperties) {
        if (!mvcProperty.isEmpty() && !mvcProperty.isValid()) {
          MessageDisplay.error(mvcProperty.getIsNotValidMessage());
          mvcProperty.requestFocus();
          return false;
        }
      }
      if (!command.performCustomValidation())
        return false;
      if (!command.isExecutable()) {
        String message = "Die Anweisung kann nicht ausgeführt werden!";
        try {
          String commandName = command.getName();
          if (commandName != null) {
            message = "\"" + commandName + "\" kann nicht ausgeführt werden";
          }
        } catch (Throwable e) {
          logError("Could not get command's name", e);
        }
        try {
          String customMessage = command.getCustomIsNotExecutableMessage();
          message += customMessage != null ? ":\n\n" + customMessage : "!";
        } catch (Throwable e) {
          logError("Could not get command's custom message", e);
        }
        return MessageDisplay.error(message);
      }
    } catch (Throwable e) {
      logError("Could not query command's executability", e);
      return false;
    }
    try {
      return command.execute();
    } catch (Throwable e) {
      logError("Could not execute command", e);
      String message = "Die Ausführung der Anweisung ist fehlgeschlagen!";
      try {
        String commandName = command.getName();
        if (commandName != null) {
          message = "\"" + commandName + "\" ist fehlgeschlagen!";
        }
      } catch (Throwable t) {
        logError("Could not get failed command's name", t);
      }
      return MessageDisplay.error(message);
    }
  }

  private static void logError(String message, Throwable t) {
    ErrorLogger.logError(MvcCommandManager.class, message, t);
  }
}
