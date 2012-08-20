package de.baeckerit.jdk.util.getter;

public interface IStringGetter<T> extends IGetter<String, T> {
  @Override
  public String get(T object);
}
