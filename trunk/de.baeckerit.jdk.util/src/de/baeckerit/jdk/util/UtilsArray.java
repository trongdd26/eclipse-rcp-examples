package de.baeckerit.jdk.util;

import java.lang.reflect.Array;
import java.util.List;

/**
 * Array utilities not found elsewhere.
 * 
 * @author Andreas Baecker
 */
public abstract class UtilsArray {

  /** Empty array. */
  public static final Object[] NO_OBJECTS = new Object[0];

  /**
   * Concatenates two arrays.
   * 
   * @param a1 the first array, which may be null.
   * @param a2 the second array, which may be null.
   * @param componentType component type of the resulting array.
   * 
   * @return the concatenation of the two arrays. Returns an empty array if both input arrays are
   *         null.
   */
  public static Object[] concat(Object[] a1, Object[] a2, Class<?> componentType) {
    if (a1 == null || a1.length == 0) {
      if (a2 == null || a2.length == 0) {
        return componentType == Object.class ? NO_OBJECTS : (Object[]) Array.newInstance(componentType, 0);
      } else {
        Object[] result = (Object[]) Array.newInstance(componentType, a2.length);
        System.arraycopy(a2, 0, result, 0, a2.length);
        return result;
      }
    } else {
      if (a2 == null || a2.length == 0) {
        Object[] result = (Object[]) Array.newInstance(componentType, a1.length);
        System.arraycopy(a1, 0, result, 0, a1.length);
        return result;
      } else {
        Object[] result = (Object[]) Array.newInstance(componentType, a1.length + a2.length);
        System.arraycopy(a1, 0, result, 0, a1.length);
        System.arraycopy(a2, 0, result, a1.length, a2.length);
        return result;
      }
    }
  }

  /**
   * Concatenates two arrays. The resulting array has component type {@link Object}.
   * 
   * @param a1 first array.
   * @param a2 second array.
   * 
   * @return the concatenation of the two arrays.
   */
  public static Object[] concat(Object[] a1, Object[] a2) {
    return concat(a1, a2, Object.class);
  }

  @SuppressWarnings("unchecked")
  public static <T> T[] toArray(List<? extends T> list, Class<? extends T> clazz) {
    if (list == null)
      return (T[]) Array.newInstance(clazz, 0);
    return list.toArray((T[]) Array.newInstance(clazz, list.size()));
  }
}
