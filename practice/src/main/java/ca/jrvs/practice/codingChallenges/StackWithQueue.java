package ca.jrvs.practice.codingChallenges;

import java.util.Deque;
import java.util.LinkedList;

public class StackWithQueue<E> {

  private Deque<E> queue1;
  private Deque<E> queue2;
  private Deque<E> queue;

  public StackWithQueue() {
    queue1 = new LinkedList<>();
    queue2 = new LinkedList<>();
    queue = new LinkedList<>();
  }

  public int sizeTwoQueues() {
    return queue1.size();
  }

  public int sizeOneQueue() {
    return queue.size();
  }

  public E peekTwoQueues() {
    return queue1.peekLast();
  }

  public E peekOneQueue() {
    return queue.peekFirst();
  }

  /**
   * Big-O: O(1)
   * Justification: adding node to LinkedList is O(1) (tail reference)
   */
  public void pushTwoQueues(E element) {
    queue1.addLast(element);
  }

  /**
   * Big-O: O(n)
   * Justification: have to iterate through (n - 1) elements
   *                to pop the head of the stack (tail of queue)
   *
   * Enqueue first (n - 1) elements in queue1 to queue2; remove last element in queue1 (top of stack);
   * switch pointers so queue1 now points to queue2 and vice-versa (queue2 is a helper queue for popping operation)
   */
  public E popTwoQueues() {
    while (queue1.size() > 1) {
      queue2.addLast(queue1.removeFirst());
    }

    E top = queue1.removeFirst();

    Deque<E> tmp = queue1;
    queue1 = queue2;
    queue2 = tmp;

    return top;
  }

  /**
   * Big-O: O(n)
   * Justification: with each push we reverse the order of the elements in the queue so that it keeps the order of a stack
   */
  public void pushOneQueue(E element) {
    queue.addLast(element);

    int size = queue.size();

    while (size > 1) {
      queue.addLast(queue.removeFirst());
      size--;
    }
  }

  /**
   * Big-O: O(1)
   * Justification: elements in queue are alreay stack order from the push operation, therefore
   *                popping is just removing the first element of the queue (dequeing)
   */
  public E popOneQueue() {
    return queue.removeFirst();
  }

}
