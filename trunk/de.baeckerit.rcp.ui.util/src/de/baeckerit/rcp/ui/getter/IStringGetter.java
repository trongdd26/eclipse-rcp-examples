package de.baeckerit.rcp.ui.getter;

public interface IStringGetter<T> extends IGetter<String, T> {
    String get(T object);
}
