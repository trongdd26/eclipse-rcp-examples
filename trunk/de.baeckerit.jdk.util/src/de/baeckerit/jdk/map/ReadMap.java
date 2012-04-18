package de.baeckerit.jdk.map;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class ReadMap implements Map<String, Object> {

  private final Object[] elements;

  public ReadMap(String k1, Object v1, String k2, Object v2, String k3, Object v3, String k4, Object v4, String k5, Object v5) {
    elements = new Object[10];
    elements[0] = k1;
    elements[1] = v1;
    elements[2] = k2;
    elements[3] = v2;
    elements[4] = k3;
    elements[5] = v3;
    elements[6] = k4;
    elements[7] = v4;
    elements[8] = k5;
    elements[9] = v5;
  }

  public ReadMap(String k1, Object v1, String k2, Object v2, String k3, Object v3, String k4, Object v4) {
    elements = new Object[8];
    elements[0] = k1;
    elements[1] = v1;
    elements[2] = k2;
    elements[3] = v2;
    elements[4] = k3;
    elements[5] = v3;
    elements[6] = k4;
    elements[7] = v4;
  }

  public ReadMap(String k1, Object v1, String k2, Object v2, String k3, Object v3) {
    elements = new Object[6];
    elements[0] = k1;
    elements[1] = v1;
    elements[2] = k2;
    elements[3] = v2;
    elements[4] = k3;
    elements[5] = v3;
  }

  public ReadMap(String k1, Object v1, String k2, Object v2) {
    elements = new Object[4];
    elements[0] = k1;
    elements[1] = v1;
    elements[2] = k2;
    elements[3] = v2;
  }

  public ReadMap(String k1, Object v1) {
    elements = new Object[2];
    elements[0] = k1;
    elements[1] = v1;
  }

  public void clear() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Object clone() {
    return this;
  }

  public boolean containsKey(Object key) {
    for (int i = 0; i < elements.length; i = i + 2)
      if (elements[i].equals(key))
        return true;
    return false;
  }

  public boolean containsValue(Object value) {
    for (int i = 1; i < elements.length; i = i + 2) {
      Object e = elements[i];
      if ((e == null && value == null) || (e != null && e.equals(value)))
        return true;
    }
    return false;
  }

  public Set<Entry<String, Object>> entrySet() {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean equals(Object o) {
    if (o == this || o == null || o.getClass() != ReadMap.class)
      return o == this;

    ReadMap other = (ReadMap) o;
    if (elements.length != other.elements.length)
      return false;

    for (int i = 0; i < elements.length; i++) {
      Object e1 = elements[i];
      Object e2 = other.elements[i];
      if ((e1 == null && e2 != null) || (e1 != null && !e1.equals(e2)))
        return false;
    }
    return true;
  }

  public Object get(Object key) {
    for (int i = 0; i < elements.length; i = i + 2)
      if (elements[i].equals(key))
        return elements[i + 1];
    return null;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    for (int i = 0; i < elements.length; i = i + 2) {
      hash += elements[i].hashCode();
    }
    return hash;
  }

  public boolean isEmpty() {
    return false;
  }

  public Set<String> keySet() {
    throw new UnsupportedOperationException();
  }

  public Object put(String key, Object value) {
    throw new UnsupportedOperationException();
  }

  public void putAll(Map<? extends String, ? extends Object> map) {
    throw new UnsupportedOperationException();
  }

  public Object remove(Object key) {
    throw new UnsupportedOperationException();
  }

  public int size() {
    return elements.length / 2;
  }

  public Collection<Object> values() {
    throw new UnsupportedOperationException();
  }
}