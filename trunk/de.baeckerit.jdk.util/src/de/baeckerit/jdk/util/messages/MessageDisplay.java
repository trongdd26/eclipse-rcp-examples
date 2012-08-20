package de.baeckerit.jdk.util.messages;

import de.baeckerit.jdk.util.errors.ErrorLogger;


public class MessageDisplay {

  public static IDisplayMessages messageDisplay = null;

  public static boolean information(String message) {
    if (messageDisplay == null) {
      ErrorLogger.logInfo(MessageDisplay.class, message);
    } else {
      messageDisplay.displayInformation(message);
    }
    return false;
  }

  public static boolean error(String message) {
    if (messageDisplay == null) {
      ErrorLogger.logError(MessageDisplay.class, message);
    } else {
      messageDisplay.displayError(message);
    }
    return false;
  }

  public static boolean ask(String message, boolean defaultAnswer) {
    if (messageDisplay == null) {
      ErrorLogger.logInfo(MessageDisplay.class, message);
      return defaultAnswer;
    } else
      return messageDisplay.displayYesNo(message, defaultAnswer);
  }
}
