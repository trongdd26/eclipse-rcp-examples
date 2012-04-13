package de.baeckerit.rcp.ui.getter;

public interface IGetter<V, T> {
    V get(T object);
}
