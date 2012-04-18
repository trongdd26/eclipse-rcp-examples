package de.baeckerit.jdk.util;

public interface ILogErrors {
  void logInfo(Class<?> clazz, String message);
  void logWarn(Class<?> clazz, String message);
  void logError(Class<?> clazz, String message);
  void logError(Class<?> clazz, String message, Throwable t);
}
