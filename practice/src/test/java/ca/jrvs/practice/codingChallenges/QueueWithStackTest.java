package ca.jrvs.practice.codingChallenges;

import static org.junit.Assert.*;

import org.junit.Test;

public class QueueWithStackTest {

  QueueWithStack<String> queue = new QueueWithStack<>();

  @Test
  public void enqueueApproachOne() {
    queue.enqueueApproachOne("apple");
    queue.enqueueApproachOne("orange");
    queue.enqueueApproachOne("pear");

    queue.enqueueApproachOne("grape");
    queue.enqueueApproachOne("banana");

    assertEquals(queue.peekApproachOne(), "apple");
    assertEquals(queue.sizeApproachOne(), 5);
  }

  @Test
  public void dequeueApproachOne() {
    queue.enqueueApproachOne("apple");
    queue.enqueueApproachOne("orange");
    queue.enqueueApproachOne("pear");

    assertEquals(queue.dequeueApproachOne(), "apple");
    assertEquals(queue.sizeApproachOne(), 2);
    assertEquals(queue.dequeueApproachOne(), "orange");
    assertEquals(queue.sizeApproachOne(), 1);
  }

  @Test
  public void enqueueApproachTwo() {
    queue.enqueueApproachTwo("apple");
    queue.enqueueApproachTwo("orange");
    queue.enqueueApproachTwo("pear");

    queue.enqueueApproachTwo("grape");
    queue.enqueueApproachTwo("banana");

    assertEquals(queue.peekApproachTwo(), "apple");
    assertEquals(queue.sizeApproachTwo(), 5);
  }

  @Test
  public void dequeueApproachTwo() {
    queue.enqueueApproachTwo("apple");
    queue.enqueueApproachTwo("orange");
    queue.enqueueApproachTwo("pear");

    assertEquals(queue.dequeueApproachTwo(), "apple");
    assertEquals(queue.sizeApproachTwo(), 2);
    assertEquals(queue.dequeueApproachTwo(), "orange");
    assertEquals(queue.sizeApproachTwo(), 1);
  }

}