package ca.jrvs.practice.dataStructures.list;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class LinkedJListTest {

  JList<String> myList;
  List<String> otherList = new ArrayList<String>(Arrays.asList("Dex", "Valen", "Jo"));

  @Before
  public void setUp() {
    myList = new LinkedJList<>();
    myList.add(otherList.get(0));
    myList.add(otherList.get(1));
    myList.add(otherList.get(2));
  }

  @Test
  public void add() {
    myList.add("Alf");
    assertTrue(myList.get(3).equals("Alf") && myList.size() == 4);
  }

  @Test
  public void addFirst() {
    myList.addFirst("Gero");
    assertTrue(myList.get(0).equals("Gero") && myList.size() == 4);
  }

  @Test
  public void toArray() {
    assertArrayEquals(myList.toArray(), otherList.toArray());
  }

  @Test
  public void size() {
    assertTrue(myList.size() == 3);
  }

  @Test
  public void isEmpty() {
    assertTrue(!myList.isEmpty());
  }

  @Test
  public void indexOf() {
    assertTrue(myList.indexOf("Valen") == 1);
  }

  @Test
  public void contains() {
    assertTrue(myList.contains(otherList.get(0)));
  }

  @Test
  public void get() {
    assertTrue(myList.get(2).equals(otherList.get(2)));
  }

  @Test
  public void remove() {
    assertTrue(myList.remove(1).equals("Valen"));
    assertTrue(!myList.contains("Valen"));
    assertTrue(myList.size() == 2);
  }

  @Test
  public void removeFirst() {
    myList.removeFirst();
    assertTrue(myList.get(0).equals("Valen"));
    assertTrue(myList.size() == 2);
  }

  @Test
  public void removeLast() {
    myList.removeLast();
    assertTrue(myList.get(myList.size() - 1).equals("Valen"));
    assertTrue(myList.size() == 2);
  }

  @Test
  public void clear() {
    myList.clear();
    assertTrue(myList.size() == 0);
  }
}