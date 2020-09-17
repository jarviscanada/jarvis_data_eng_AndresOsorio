package ca.jrvs.practice.codingChallenges;

import static org.junit.Assert.*;

import ca.jrvs.practice.codingChallenges.LinkedListCycle.ListNode;
import org.junit.Test;

public class LinkedListCycleTest {

  ListNode node1;

  @Test
  public void hasCycle() {
    ListNode node4 = new ListNode(4, null);
    ListNode node3 = new ListNode(0, node4);
    ListNode node2 = new ListNode(2, node3);
    node1 = new ListNode(3, node2);
    node4.next = node2;

    assertTrue(LinkedListCycle.hasCycle(node1));

    node4.next = null;

    assertFalse(LinkedListCycle.hasCycle(node1));
  }
}