package ca.jrvs.practice.codingChallenges;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Ticket: https://www.notion.so/How-to-compare-two-maps-dd704cf4b6bc4e5e8a4d94f043a9b5b4
 */
public class MapEquals {

  /**
   * Big-O: O(n)
   * Justification: have to compare all the elements in the maps
   */
  public <K, V> boolean nodeEquals(Map<K, V> m1, Map<K, V> m2) {
    Set<Map.Entry<K, V>> keys1 =  m1.entrySet();
    Set<Map.Entry<K, V>> keys2 =  m2.entrySet();

    Iterator<Entry<K, V>> iterator1 = keys1.iterator();
    Iterator<Entry<K, V>> iterator2 = keys2.iterator();

    while (iterator1.hasNext()) {
      if (!iterator1.next().equals(iterator2.next()))
        return false;
    }

    return true;
  }

  /**
   * Big-O: O(n)
   * Justification: have to compare all the elements in the maps
   */
  public <K, V> boolean mapEquals(HashMap<K, V> m1, HashMap<K, V> m2) {
    return m1.equals(m2);
  }

}
