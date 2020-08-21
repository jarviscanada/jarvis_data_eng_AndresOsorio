package ca.jrvs.practice.codingChallenges;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.junit.Test;

public class DuplicateLinkedListNodeTest {

  List<Integer> list = new LinkedList<>(Arrays.asList(4, 4, 2, 1, 4, 2, 5, 6, 5));
  List<Integer> list2 = new LinkedList<>(Arrays.asList(4, 2, 1, 5, 6));

  @Test
  public void removeDuplicates() {
    DuplicateLinkedListNode.removeDuplicates(list);
    assertTrue(list.equals(list2));
  }
}