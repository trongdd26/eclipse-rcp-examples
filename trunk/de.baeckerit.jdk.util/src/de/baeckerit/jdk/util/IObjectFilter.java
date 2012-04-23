package de.baeckerit.jdk.util;

public interface IObjectFilter<T> {
    boolean select(T object);
}
