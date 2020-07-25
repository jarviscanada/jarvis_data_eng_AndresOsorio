package ca.jrvs.practice.dataStructures.list;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ArrayJListTest {

  JList<String> myList;
  String[] otherList = {"Dex", "Valen", "Jo"};

  @Before
  public void setUp() {
    myList = new ArrayJList<>();
    myList.add(otherList[0]);
    myList.add(otherList[1]);
    myList.add(otherList[2]);
  }

  @Test
  public void add() {
    assertTrue(myList.add("Rob"));
  }

  @Test
  public void toArray() {
    assertArrayEquals(otherList, myList.toArray());
  }

  @Test
  public void size() {
    assertTrue(myList.size() == otherList.length);
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
    assertTrue(myList.contains("Jo"));
  }

  @Test
  public void get() {
    assertTrue(myList.get(2).equals("Jo"));
  }

  @Test
  public void remove() {
    assertTrue(myList.remove(1).equals("Valen"));
    assertTrue(!myList.contains("Valen"));
    assertTrue(myList.size() == 2);
  }

  @Test
  public void clear() {
    myList.clear();
    assertTrue(myList.isEmpty());
  }
}