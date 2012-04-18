package de.baeckerit.jdk.util;

public interface IDisplayMessages {

  void displayInformation(String message);
  void displayError(String message);
  boolean displayYesNo(String message, boolean defaultAnswer);
}
