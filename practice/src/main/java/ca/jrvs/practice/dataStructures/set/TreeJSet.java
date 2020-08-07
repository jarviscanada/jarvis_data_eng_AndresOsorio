package ca.jrvs.practice.dataStructures.set;

import ca.jrvs.practice.dataStructures.tree.JBSTree;
import java.util.Comparator;

public class TreeJSet<E> implements JSet<E> {

  JBSTree<E> tree;

  public TreeJSet(Comparator<E> comparator) {
    tree = new JBSTree<>(comparator);
  }

  @Override
  public int size() {
    return tree.size();
  }

  @Override
  public boolean contains(Object o) {
    if (tree.search((E)o) != null)
      return (tree.search((E)o).equals(o));

    return false;
  }

  @Override
  public boolean add(E e) {
    if (tree.search(e) == null)
      return (tree.insert(e) == e);

    return false;
  }

  @Override
  public boolean remove(Object o) {
    return (tree.remove((E)o).equals(o));
  }

  @Override
  public void clear() {
    tree.clear();
  }

  public E[] orderedElements() {
    return tree.inOrder();
  }
}
