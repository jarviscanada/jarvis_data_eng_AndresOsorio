package ca.jrvs.practice.codingChallenges;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Ticket: https://www.notion.so/Implement-Queue-using-Stacks-33514101b956480795eda2301f6c106a
 */
public class QueueWithStack<E> {

  private Deque<E> stack1;
  private Deque<E> stack2;
  private E front;

  public QueueWithStack() {
    stack1 = new LinkedList<>();
    stack2 = new LinkedList<>();
  }

  public int sizeApproachOne() {
    return stack1.size();
  }

  public int sizeApproachTwo() {
    return stack1.size() + stack2.size();
  }

  public E peekApproachOne() {
    return stack1.peekFirst();
  }

  public E peekApproachTwo() {
    if (stack2.isEmpty())
      return front;
    else
      return stack2.peekFirst();
  }

  /**
   * Big-O:         O(n) time; O(n) space.
   * Justification: we have to keep a queue order (FIFO) but stack order is LIFO;
   *                therefore we pop all elements in stack1 and push them into stack2;
   *                then we push the new element into stack1 (bottom of stack - tail of queue)
   *                and bring back elements temporarily stored in stack2; this keeps a queue order.
   */
  public void enqueueApproachOne(E e) {
    if (stack1.isEmpty()) {
      stack1.addFirst(e);
    } else {
      while (!stack1.isEmpty())
        stack2.addFirst(stack1.removeFirst());

      stack1.addFirst(e);

      while (!stack2.isEmpty())
        stack1.addFirst(stack2.removeFirst());
    }
  }

  /**
   * Big-O:         O(1) time; O(1) space.
   * Justification: push operation keeps stack1 in queue order, therefore we just have to pop the top element.
   */
  public E dequeueApproachOne() {
    return stack1.removeFirst();
  }

  /**
   * Big-O:         O(1) time; O(1) space.
   * Justification: we just push elements into the stack normally.
   */
  public void enqueueApproachTwo(E e) {
    if (stack1.isEmpty())
      // have to keep track of front bc it might be at the bottom of stack1
      front = e;
    stack1.addFirst(e);
  }

  /**
   * Big-O:         Amortized O(1) time; O(n) space.
   * Justification: we use stack1 as an auxiliary storage; stack1 has elements in LIFO order while stack2 has elements in FIFO order;
   *                we pop elements from stack1 and push them onto stack2, which gives as a FIFO order and the top of stack2 is the front of the queue;
   *                in the worst case stack2 is empty and we transfer all elements from stack1 to stack2 (O(n));
   *                however, normally stack2 is not empty an contains the front of the stack as its top element and therefore dequeueing is O(1) on average;
   *                the first dequeue operation is O(n) bc we have to transfer all elements from stack1 to stack2, after that, the worst
   *                case scenario only happens after n dequeues, which leaves stack2 empty again and therefore there's a need to transfer
   *                all n elements again.
   */
  public E dequeueApproachTwo() {
    if (stack2.isEmpty()) {
      while (!stack1.isEmpty())
        stack2.addFirst(stack1.removeFirst());
    }
    return stack2.removeFirst();
  }

}
