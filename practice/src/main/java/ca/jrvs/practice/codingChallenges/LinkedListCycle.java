package ca.jrvs.practice.codingChallenges;

/**
 * Ticket: https://www.notion.so/LinkedList-Cycle-dadedc9fa1bf46fabc81fedd3a5529b2
 */
public class LinkedListCycle {

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
   * Justification: if there is no cycle we consider n/2 nodes; if there is a cycle both pointers meet each other after O(n) iterations.
   *
   * Use 2 pointers; the slow pointer moves 1 node at a time, the fast pointer moves 2 nodes at a time;
   * if there's no cycle in the list, the fast pointer will eventually reach the end;
   * however, if there's a cycle then the fast pointer will circle back around and eventually meet the slow pointer;
   * a cycle occurs when the fast pointer == the slow pointer after looping around; we make sure this equality only holds
   * in this scenario (and not before looping around) by starting slow at head and fast at head.next.
   */
  public static boolean hasCycle(ListNode head) {
    if (head == null || head.next == null)
      return false;

    ListNode slow = head;
    ListNode fast = head.next;

    while (slow != fast) {
      if (fast == null || fast.next == null)
        return false;

      slow = slow.next;
      fast = fast.next.next;
    }

    return true;
  }

}
