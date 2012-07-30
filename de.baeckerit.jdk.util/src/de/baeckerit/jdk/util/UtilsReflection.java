package de.baeckerit.jdk.util;

import java.lang.reflect.Field;

public abstract class UtilsReflection {

  public static Object getMember(Object object, Class<?> clazz, String name) {
    try {
      Field field = clazz.getDeclaredField(name);
      field.setAccessible(true);
      return field.get(object);
    } catch (Exception e) {
      return e;
    }
  }
}
