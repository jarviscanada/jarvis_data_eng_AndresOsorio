package ca.jrvs.practice.codingChallenges;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StackWithQueueTest {

  StackWithQueue<String> stack;

  @Before
  public void setUp() throws Exception {
    stack = new StackWithQueue<>();
    stack.pushTwoQueues("apple");
    stack.pushTwoQueues("orange");
    stack.pushTwoQueues("pear");

    stack.pushOneQueue("apple");
    stack.pushOneQueue("orange");
    stack.pushOneQueue("pear");
  }

  @Test
  public void pushTwoQueues() {
    stack.pushTwoQueues("grape");
    stack.pushTwoQueues("banana");
    assertTrue(stack.peekTwoQueues().equals("banana"));
    assertTrue(stack.sizeTwoQueues() == 5);
  }

  @Test
  public void popTwoQueues() {
    assertTrue(stack.popTwoQueues().equals("pear"));
    assertTrue(stack.sizeTwoQueues() == 2);
    assertTrue(stack.popTwoQueues().equals("orange"));
    assertTrue(stack.sizeTwoQueues() == 1);
  }

  @Test
  public void pushOneQueue() {
    stack.pushOneQueue("grape");
    stack.pushOneQueue("banana");
    assertTrue(stack.peekOneQueue().equals("banana"));
    assertTrue(stack.sizeOneQueue() == 5);
  }

  @Test
  public void popOneQueue() {
    assertTrue(stack.popOneQueue().equals("pear"));
    assertTrue(stack.sizeOneQueue() == 2);
    assertTrue(stack.popOneQueue().equals("orange"));
    assertTrue(stack.sizeOneQueue() == 1);
  }
}