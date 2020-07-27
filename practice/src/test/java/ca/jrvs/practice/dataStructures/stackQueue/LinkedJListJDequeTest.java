package ca.jrvs.practice.dataStructures.stackQueue;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import jdk.nashorn.internal.scripts.JD;
import org.junit.Before;
import org.junit.Test;

public class LinkedJListJDequeTest {

  JDeque<String> data;
  List<String> list = new ArrayList<String>(Arrays.asList("Dex", "Valen", "Jo"));

  @Before
  public void setUp() throws Exception {
    data = new LinkedJListJDeque<String>();
    data.add(list.get(0));
    data.add(list.get(1));
    data.add(list.get(2));
  }

  @Test
  public void add() {
    data.add("Rob");
    assertTrue(data.peek().equals("Dex"));
  }

  @Test
  public void remove() {
    assertTrue(data.remove().equals("Dex"));
    assertTrue(data.peek().equals("Valen"));
  }

  @Test
  public void pop() {
    assertTrue(data.pop().equals("Dex"));
    assertTrue(data.peek().equals("Valen"));
  }

  @Test
  public void push() {
    data.push("Alice");
    data.push("Ron");
    assertTrue(data.peek().equals("Ron"));
  }

  @Test
  public void peek() {
    assertTrue(data.peek().equals("Dex"));
    data.push("Ryan");
    assertTrue(data.peek().equals("Ryan"));
  }
}