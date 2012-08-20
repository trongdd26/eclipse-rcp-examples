package de.baeckerit.jdk.util.errors;

public interface ILogErrors {
  boolean isDebugEnabled(Class<?> clazz);
  
  void logDebug(Class<?> clazz, String message);
  void logInfo(Class<?> clazz, String message);
  void logWarn(Class<?> clazz, String message);
  void logError(Class<?> clazz, String message);
  void logError(Class<?> clazz, String message, Throwable t);
}
