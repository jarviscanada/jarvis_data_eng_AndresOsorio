package ca.jrvs.practice.dataStructures.set;

import java.util.HashMap;
import java.util.Map;

public class HashJSet<E> implements JSet<E> {

  private Map<E, Object> map;

  // Dummy value to associate with an Object in the backing Map
  private static final Object PRESENT = new Object();

  public HashJSet() {
    map = new HashMap<>();
  }

  @Override
  public int size() {
    return map.size();
  }

  @Override
  public boolean contains(Object o) {
    return map.containsKey(o);
  }

  @Override
  public boolean add(E e) {
    return (map.put(e, PRESENT) == null);
  }

  @Override
  public boolean remove(Object o) {
    return (map.remove(o) == PRESENT);
  }

  @Override
  public void clear() {
    map.clear();
  }
}
