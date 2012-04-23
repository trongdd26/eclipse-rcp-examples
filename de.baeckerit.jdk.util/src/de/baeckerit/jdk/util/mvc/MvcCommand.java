package de.baeckerit.jdk.util.mvc;


public abstract class MvcCommand {

  private final String name;

  public MvcCommand() {
    name = null;
  }

  public MvcCommand(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public abstract boolean execute();

  public boolean isExecutable() {
    return true;
  }

  public MvcProperty<?>[] getRequiredProperties() {
    return MvcProperty.NO_PROPERTIES;
  }

  public MvcProperty<?>[] getOptionalProperties() {
    return MvcProperty.NO_PROPERTIES;
  }

  public String getCustomIsNotExecutableMessage() {
    return null;
  }

  public boolean performCustomValidation() {
    return true;
  }
}
