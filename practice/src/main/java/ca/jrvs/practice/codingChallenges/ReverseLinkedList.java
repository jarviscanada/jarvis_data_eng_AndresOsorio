package ca.jrvs.practice.codingChallenges;

/**
 * Ticket: https://www.notion.so/Reverse-Linked-List-75c97196e24f451d91e770d42af571c9
 */
public class ReverseLinkedList {

  // Singly LinkedList definition
  public static class ListNode {

    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }

  }

  /**
   * Big-O:         O(n) time; O(1) space.
   * Justification: we iterate through the list once.
   */
  public static ListNode iterative(ListNode head) {
    ListNode prev = null;
    ListNode current = head;

    while (current != null) {
      // keep track of current's next node
      ListNode next = current.next;
      current.next = prev;
      prev = current;
      current = next;
    }

    return prev;
  }

  /**
   * Big-O:         O(n) time; O(n) space.
   * Justification: we make n recursive calls and each call does O(1) operations.
   */
  public static ListNode recursive(ListNode head) {
    // last node will return itself (newHead)
    if (head == null || head.next == null)
      return head;

    // go all the way to the end of the list first
    ListNode newHead = recursive(head.next);
    head.next.next = head;
    // will be filled in next call; also makes the last node point to null, otherwise we'd have a cycle
    head.next = null;

    return newHead;
  }

}
