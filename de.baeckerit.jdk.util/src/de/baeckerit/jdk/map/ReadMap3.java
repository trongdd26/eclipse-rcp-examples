package de.baeckerit.jdk.map;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Keys must not be null.
 * 
 * @author Andreas
 */
public class ReadMap3 implements Map<String, Object> {

  private final String k1, k2, k3;
  private final Object v1, v2, v3;

  public ReadMap3(String k1, Object v1, String k2, Object v2, String k3, Object v3) {
    this.k1 = k1;
    this.v1 = v1;
    this.k2 = k2;
    this.v2 = v2;
    this.k3 = k3;
    this.v3 = v3;
  }

  public void clear() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Object clone() {
    return this;
  }

  public boolean containsKey(Object key) {
    return k1.equals(key) || k2.equals(key) || k3.equals(key);
  }

  public boolean containsValue(Object value) {
    if ((v1 == null && value == null) || (v1 != null && v1.equals(value)))
      return true;
    if ((v2 == null && value == null) || (v2 != null && v2.equals(value)))
      return true;
    if ((v3 == null && value == null) || (v3 != null && v3.equals(value)))
      return true;
    return false;
  }

  public Set<Entry<String, Object>> entrySet() {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean equals(Object o) {
    if (o == this || o == null || o.getClass() != ReadMap3.class)
      return o == this;

    ReadMap3 other = (ReadMap3) o;

    if (!k1.equals(other.k1) || !k2.equals(other.k2) || !k3.equals(other.k3))
      return false;

    if ((v1 == null && other.v1 != null) || (v1 != null && !v1.equals(other.v1)))
      return false;
    if ((v2 == null && other.v2 != null) || (v2 != null && !v2.equals(other.v2)))
      return false;
    if ((v3 == null && other.v3 != null) || (v3 != null && !v3.equals(other.v3)))
      return false;

    return true;
  }

  public Object get(Object key) {
    if (k1.equals(key))
      return v1;
    if (k2.equals(key))
      return v2;
    if (k3.equals(key))
      return v3;
    return null;
  }

  @Override
  public int hashCode() {
    int hash = k1.hashCode() + k2.hashCode() + k3.hashCode();
    if (v1 != null) {
      hash += v1.hashCode();
    }
    if (v2 != null) {
      hash += v2.hashCode();
    }
    if (v3 != null) {
      hash += v3.hashCode();
    }
    return hash;
  }

  public int size() {
    return 3;
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

  public Collection<Object> values() {
    throw new UnsupportedOperationException();
  }
}