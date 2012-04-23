package de.baeckerit.jdk.util.mvc;

public class IllegalLengthException extends IllegalArgumentException {

  private static final long serialVersionUID = 1L;

  public IllegalLengthException(String s, int maxLength) {
    super("length of string must not be greater than " + maxLength);
  }
}
