package ca.jrvs.practice.dataStructures.tree;

import static org.junit.Assert.*;

import ca.jrvs.practice.dataStructures.list.AgeComparator;
import ca.jrvs.practice.dataStructures.list.Employee;
import java.util.Comparator;
import org.junit.Before;
import org.junit.Test;

public class JBSTreeTest {

  JTree<Integer> tree;
  Object[] preorder, postorder, inorder;

  @Before
  public void setUp() throws Exception {
    Comparator<Integer> comparator = (Integer i1, Integer i2) -> i1.intValue() - i2.intValue();
    tree = new JBSTree<>(comparator);
    tree.insert(10);
    tree.insert(2);
    tree.insert(3);
    tree.insert(4);
    tree.insert(12);
    tree.insert(20);
    tree.insert(15);

    preorder = new Object[]{10, 2, 3, 4, 12, 20, 15};
    postorder = new Object[]{4, 3, 2, 15, 20, 12, 10};
    inorder = new Object[]{2, 3, 4, 10, 12, 15, 20};
  }

  @Test
  public void insert() {
    assertTrue(tree.insert(1) == 1);
  }

  @Test
  public void search() {
    assertTrue(tree.search(12) == 12);
    assertTrue(tree.search(7) == null);
    tree.insert(7);
    assertTrue(tree.search(7) == 7);
  }

  @Test
  public void remove() {
    assertTrue(tree.remove(15) == 15);
    int  i = tree.remove(12);
    assertTrue(i == 12);
    i = tree.remove(10);
    assertTrue(i == 10);
  }

  @Test
  public void preOrder() {
    assertArrayEquals(tree.preOrder(), preorder);
  }

  @Test
  public void inOrder() {
    assertArrayEquals(tree.inOrder(), inorder);
  }

  @Test
  public void postOrder() {
    assertArrayEquals(tree.postOrder(), postorder);
  }

  @Test
  public void findHeight() {
    assertTrue(tree.findHeight() == 3);
    tree.insert(13);
    assertTrue(tree.findHeight() == 4);
  }
}