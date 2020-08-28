package ca.jrvs.practice.codingChallenges;

/**
 * Ticket: https://www.notion.so/Middle-of-the-Linked-List-d0e7d95148ce45bba7189fa3db90f231
 */
public class MiddleOfLinkedList {

  // Singly LinkedList definition
  public static class ListNode {
    int val;
    ListNode next;

    ListNode() { };

    ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }
  }

  /**
   * Big-O:         O(n) time; O(1) space.
   * Justification: we traverse 1/2n nodes.
   *
   * Use 2 pointers; one moves 2 nodes at a time (fast pinter), the other moves 1 node at a time (fast pointer);
   * when the fast pointer reaches the end of the list, the slow pointer points to the middle node.
   * Can also be done by removing the (n / 2)th node from the end of the list.
   */
  public static ListNode middleNode(ListNode head) {
    ListNode slow = head;
    ListNode fast = head;

    while (fast != null && fast.next != null) {
      fast = fast.next.next;
      slow = slow.next;
    }

    return slow;
  }

}
