package ca.jrvs.practice.codingChallenges;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Ticket: https://www.notion.so/Duplicate-LinkedList-Node-7f054b107cb34cda9e50c3253b1733ec
 */
public class DuplicateLinkedListNode {

  /**
   * Big-O:         O(n^2) time; O(n) space
   * Justification: we have to iterate through the whole list (O(n)) and remove each duplicate (O(n)).
   *                a set doesn't allow duplicates; therefore we can check duplicates in the list with a set;
   *                if the current element is a duplicate (already in set) then we remove it and don't increment index
   *                bc we have to consider that same index again;
   */
  public static <E> void removeDuplicates(List<E> list) {
    Set<E> set = new HashSet<>();
    E e = null;

    int i = 0;
    while (i < list.size()) {
      e = list.get(i);
      if (set.add(e))
        i++;
      else
        list.remove(i);
    }
  }

}
