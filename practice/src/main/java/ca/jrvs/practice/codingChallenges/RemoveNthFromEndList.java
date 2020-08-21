package ca.jrvs.practice.codingChallenges;

/**
 * Ticket: https://www.notion.so/Nth-Node-From-End-of-LinkedList-21b07f92c4824c80bc20bec3bdfd0010
 */
public class RemoveNthFromEndList {

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
   * Big-O:         O(L) time; O(1) space; where L is the length of the input list
   * Justification: we make 2 passes through the list but the first is through the first (n + 1) nodes;
   *                the second is through the remaining (L - (n + 1)) nodes;
   *                therefore overall we make one pass through the whole list;
   *                removing the nth node from the end of the list is the same as removing the (L - (n + 1))th node;
   *                we can accomplish this by keeping two pointers; both start before head, then we move one (n + 1) nodes forward,
   *                this creates a gap of n nodes between pointers; we then move both pointers forward until the first node goes
   *                past the last node; the gap is kept and so now the second pointer points to the (L - n)th node from end of list
   *                and its next node is the one to be deleted.
   */
  public static ListNode removeNth(ListNode head, int n) {
    // dummy node to take care of edge cases (list with one node, removing head)
    ListNode dummy = new ListNode(0, head);
    // both pointers start at dummy
    ListNode first = dummy;
    ListNode second = dummy;

    // move first to (n + 1)th node to create gap of n node between first and second
    for (int i = 0; i < n + 1; i++) {
      first = first.next;
    }

    // move first to past the last node; move second whenever we move first so that the gap is maintained;
    // after this loop, first points to the last node's next (null) and second points to the (n + 1)th node from the end of list
    while (first != null) {
      first = first.next;
      second = second.next;
    }

    // second now points to (n + 1)th node from end of list, therefore second's next (nth node form end of list) is the one to be deleted
    second.next = second.next.next;

    // return head of list (can't return 'head' bc it points to the input head, which might have been changed/deleted by method)
    return dummy.next;
  }

}
