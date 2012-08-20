package de.baeckerit.jdk.util.getter;

/**
 * Getting a member of type V from an instance of T.
 * <p>
 * To be called by my JFace Viewer utilities. To be implemented by anything that
 * is presentable in some way.
 */
public interface IGetter<V, T> {
  V get(T object);
}
