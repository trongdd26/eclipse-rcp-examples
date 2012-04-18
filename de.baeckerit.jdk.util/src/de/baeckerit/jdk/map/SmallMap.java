package de.baeckerit.jdk.map;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import de.baeckerit.jdk.util.UtilsArray;

/**
 * Borrowed from Eclipse's ObjectMap. Fixed many bugs....
 */
public class SmallMap<K, V> implements Map<K, V> {
  protected static final int GROW_SIZE = 10;

  protected int count;
  protected Object[] elements;

  public SmallMap(int initialCapacity) {
    elements = new Object[initialCapacity * 2];
    count = 0;
  }

  public SmallMap(Map<? extends K, ? extends V> map) {
    this(map.size());
    putAll(map);
  }

  public SmallMap(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4) {
    this(4);
    elements[0] = k1;
    elements[1] = v1;
    elements[2] = k2;
    elements[3] = v2;
    elements[4] = k3;
    elements[5] = v3;
    elements[6] = k4;
    elements[7] = v4;
    count = 4;
  }

  public SmallMap(K k1, V v1, K k2, V v2, K k3, V v3) {
    this(3);
    elements[0] = k1;
    elements[1] = v1;
    elements[2] = k2;
    elements[3] = v2;
    elements[4] = k3;
    elements[5] = v3;
    count = 3;
  }

  public SmallMap(K k1, V v1, K k2, V v2) {
    this(2);
    elements[0] = k1;
    elements[1] = v1;
    elements[2] = k2;
    elements[3] = v2;
    count = 2;
  }

  public SmallMap(K k1, V v1) {
    this(1);
    elements[0] = k1;
    elements[1] = v1;
    count = 1;
  }

  public void clear() {
    elements = UtilsArray.NO_OBJECTS;
    count = 0;
  }

  @Override
  public Object clone() {
    return new SmallMap<K, V>(this);
  }

  public boolean containsKey(Object key) {
    for (int i = 0; i < elements.length; i = i + 2)
      if (elements[i] != null && elements[i].equals(key))
        return true;
    return false;
  }

  public boolean containsValue(Object value) {
    for (int i = 1; i < elements.length; i = i + 2)
      if (elements[i] != null && elements[i].equals(value))
        return true;
    return false;
  }

  public Set<Entry<K, V>> entrySet() {
    return toHashMap().entrySet();
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Map))
      return false;
    @SuppressWarnings("unchecked")
    Map<Object, Object> other = (Map<Object, Object>) o;
    //must be same size
    if (count != other.size())
      return false;
    //keysets must be equal
    if (!keySet().equals(other.keySet()))
      return false;
    //values for each key must be equal
    for (int i = 0; i < elements.length; i = i + 2) {
      if (elements[i] != null && (!elements[i + 1].equals(other.get(elements[i]))))
        return false;
    }
    return true;
  }

  @SuppressWarnings("unchecked")
  public V get(Object key) {
    for (int i = 0; i < elements.length; i = i + 2)
      if (elements[i] != null && elements[i].equals(key))
        return (V) elements[i + 1];
    return null;
  }

  protected void grow() {
    Object[] expanded = new Object[elements.length + GROW_SIZE];
    System.arraycopy(elements, 0, expanded, 0, elements.length);
    elements = expanded;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    for (int i = 0; i < elements.length; i = i + 2) {
      if (elements[i] != null) {
        hash += elements[i].hashCode();
      }
    }
    return hash;
  }

  public boolean isEmpty() {
    return count == 0;
  }

  @SuppressWarnings("unchecked")
  public Set<K> keySet() {
    Set<K> result = new HashSet<K>(size());
    for (int i = 0; i < elements.length; i = i + 2) {
      if (elements[i] != null) {
        result.add((K) elements[i]);
      }
    }
    return result;
  }

  @SuppressWarnings("unchecked")
  public V put(K key, V value) {
    if (key == null)
      throw new NullPointerException();
    if (value == null)
      return remove(key);

    int emptyIndex = -1;
    // replace existing value if it exists
    for (int i = 0; i < elements.length; i += 2) {
      if (elements[i] != null) {
        if (elements[i].equals(key)) {
          Object oldValue = elements[i + 1];
          elements[i + 1] = value;
          return (V) oldValue;
        }
      } else if (emptyIndex == -1) {
        // keep track of the first empty index
        emptyIndex = i;
      }
    }
    // this will put the emptyIndex greater than the size but
    // that's ok because we will grow first.
    if (emptyIndex == -1) {
      emptyIndex = count * 2;
    }

    // otherwise add it to the list of elements.
    // grow if necessary
    if (elements.length <= (count * 2)) {
      grow();
    }
    elements[emptyIndex] = key;
    elements[emptyIndex + 1] = value;
    count++;
    return null;
  }

  public void putAll(Map<? extends K, ? extends V> map) {
    for (Iterator<? extends K> i = map.keySet().iterator(); i.hasNext();) {
      K key = i.next();
      V value = map.get(key);
      put(key, value);
    }
  }

  @SuppressWarnings("unchecked")
  public V remove(Object key) {
    for (int i = 0; i < elements.length; i = i + 2) {
      if (elements[i] != null && elements[i].equals(key)) {
        elements[i] = null;
        Object result = elements[i + 1];
        elements[i + 1] = null;
        count--;
        return (V) result;
      }
    }
    return null;
  }

  public int size() {
    return count;
  }

  @SuppressWarnings("unchecked")
  private HashMap<K, V> toHashMap() {
    HashMap<K, V> result = new HashMap<K, V>(size());
    for (int i = 0; i < elements.length; i = i + 2) {
      if (elements[i] != null) {
        result.put((K) elements[i], (V) elements[i + 1]);
      }
    }
    return result;
  }

  @SuppressWarnings("unchecked")
  public Collection<V> values() {
    Set<V> result = new HashSet<V>(size());
    for (int i = 1; i < elements.length; i = i + 2) {
      if (elements[i] != null) {
        result.add((V) elements[i]);
      }
    }
    return result;
  }
}