package de.baeckerit.jdk.util.messages;

public interface IDisplayMessages {

  void displayInformation(String message);
  void displayError(String message);
  boolean displayYesNo(String message, boolean defaultAnswer);
}
