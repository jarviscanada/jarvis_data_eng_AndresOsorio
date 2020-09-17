package ca.jrvs.practice.codingChallenges;

import static org.junit.Assert.*;

import ca.jrvs.practice.codingChallenges.RemoveNthFromEndList.ListNode;
import org.junit.Before;
import org.junit.Test;

public class RemoveNthFromEndListTest {

  ListNode node1;

  @Before
  public void setUp() throws Exception {
    ListNode node5 = new ListNode(5, null);
    ListNode node4 = new ListNode(4, node5);
    ListNode node3 = new ListNode(3, node4);
    ListNode node2 = new ListNode(2, node3);
    node1 = new ListNode(1, node2);
  }

  @Test
  public void removeNth() {
    boolean removed = true;
    int count = 0;
    ListNode current = null;

    current = RemoveNthFromEndList.removeNth(node1, 2);
    while (current != null) {
      if (current.val == 4)
        removed = false;
      current = current.next;
      count++;
    }

    assertTrue(count == 4 && removed);

    count = 0;
    current = RemoveNthFromEndList.removeNth(node1, 4);
    while (current != null) {
      if (current.val == 1)
        removed = false;
      current = current.next;
      count++;
    }

    assertTrue(count == 3 && removed);
  }
}