package ca.jrvs.practice.codingChallenges;

import static org.junit.Assert.*;

import ca.jrvs.practice.codingChallenges.ReverseLinkedList.ListNode;
import org.junit.Test;

public class ReverseLinkedListTest {

  ListNode fourth = new ListNode(4, null);
  ListNode third = new ListNode(3, fourth);
  ListNode second = new ListNode(2, third);
  ListNode first = new ListNode(1, second);

  @Test
  public void iterative() {
    ListNode newHead = ReverseLinkedList.iterative(first);
    assertEquals(fourth, newHead);
    assertEquals(third, newHead.next);
    assertEquals(second, newHead.next.next);
    assertEquals(first, newHead.next.next.next);
    assertEquals(null, newHead.next.next.next.next);
  }

  @Test
  public void recursive() {
    ListNode newHead = ReverseLinkedList.recursive(first);
    assertEquals(fourth, newHead);
    assertEquals(third, newHead.next);
    assertEquals(second, newHead.next.next);
    assertEquals(first, newHead.next.next.next);
    assertEquals(null, newHead.next.next.next.next);
  }
}