package de.baeckerit.jdk.util;


public interface IStringGetter<T> extends IGetter<String, T> {
    String get(T object);
}
