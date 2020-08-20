package ca.jrvs.practice.dataStructures.tree;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * A simplified BST implementation
 *
 * @param <E> type of object to be stored
 */
public class JBSTree<E> implements JTree<E> {

  /**
   * The comparator used to maintain order in this tree map
   * Comparator cannot be null
   */
  private Comparator<E> comparator;

  private Node<E> root, searched;

  private int size;

  /**
   * Create a new BST
   *
   * @param comparator the comparator that will be used to order this map.
   * @throws IllegalArgumentException if comparator is null
   */
  public JBSTree(Comparator<E> comparator) {
    if (comparator == null)
      throw new IllegalArgumentException("ERROR: Must provide a valid comparator");

    this.comparator = comparator;
  }

  public int size() {
    return size;
  }

  public void clear() {
    root = null;
    size = 0;
  }

  /**
   * Insert an object into the BST.
   * Please review the BST property.
   *
   * @param object item to be inserted
   * @return inserted item
   * @throws IllegalArgumentException if the object already exists
   */
  @Override
  public E insert(E object) {
    size++;
    Node<E> entry = new Node<>(object, null);
    if (root == null) {
      root = entry;
      return entry.value;
    } else {
      int cmp;
      Node<E> parent = root;
      while (parent != null) {
        cmp = comparator.compare(object, parent.value);
        if (cmp < 0) {
          if (parent.left == null) {
            parent.setLeft(entry);
            parent.left.setParent(parent);
            return entry.value;
          } else {
            parent = parent.left;
          }
        } else if (cmp > 0) {
          if (parent.right == null) {
            parent.setRight(entry);
            parent.right.setParent(parent);
            return entry.value;
          } else {
            parent = parent.right;
          }
        } else {
          size--;
          throw new IllegalArgumentException("ERROR: Entry already exists");
        }
      }
    }

    return null;
  }

  /**
   * Search and return an object, return null if not found
   *
   * @param object to be found
   * @return the object if exists or null if not
   */
  @Override
  public E search(E object) {
    int cmp;
    Node<E> parent = root;
    while (parent != null) {
      cmp = comparator.compare(object, parent.value);
      if (cmp < 0) {
        parent = parent.left;
      } else if (cmp > 0) {
        parent = parent.right;
      } else {
        searched = parent;
        return parent.value;
      }
    }
    return null;
  }

  /**
   * Remove an object from the tree.
   *
   * @param object to be removed
   * @return removed object
   * @throws IllegalArgumentException if the object not exists
   */
  @Override
  public E remove(E object) {
    if (search(object) == null)
      throw new IllegalArgumentException("ERROR: element does not exist");

    size--;

    E removed = searched.value;

    if (removed.equals(root.value)) {
      root.right.left = root.left;
      root = root.right;
      return removed;
    }

    boolean isLeft = comparator.compare(searched.value, searched.parent.value) < 0;
    boolean isRight = comparator.compare(searched.value, searched.parent.value) > 0;

    if (searched.left == null && searched.right == null) {
      if (isLeft)
        searched.parent.left = null;
      else
        searched.parent.right = null;
    } else if (searched.right != null) {
      if (isLeft)
        searched.parent.left = searched.right;
      else
        searched.parent.right = searched.right;
      searched.right.parent = searched.parent;
    } else {
      if (isLeft)
        searched.parent.left = searched.left;
      else
        searched.parent.right = searched.left;
      searched.left.parent = searched.parent;
    }
    return removed;
  }

  private void preOrder(Node<E> node, List<E> ls) {
    if (node != null) {
      ls.add(node.value);
      preOrder(node.left, ls);
      preOrder(node.right, ls);
    }
  }

  private void postOrder(Node<E> node, List<E> ls) {
    if (node != null) {
      postOrder(node.left, ls);
      postOrder(node.right, ls);
      ls.add(node.value);
    }
  }

  private void inOrder(Node<E> node, List<E> ls) {
    if (node != null) {
      inOrder(node.left, ls);
      ls.add(node.value);
      inOrder(node.right, ls);
    }
  }

  /**
   * traverse the tree recursively
   *
   * @return all objects in pre-order
   */
  @Override
  public E[] preOrder() {
    E[] result = (E[])new Object[size];
    List<E> ls = new LinkedList<>();
    preOrder(root, ls);

    for (int i = 0; i < result.length; i++) {
      result[i] = ls.get(i);
    }

    return result;
  }

  /**
   * traverse the tree recursively
   *
   * @return all objects in-order
   */
  @Override
  public E[] inOrder() {
    E[] result = (E[])new Object[size];
    List<E> ls = new LinkedList<>();
    inOrder(root, ls);

    for (int i = 0; i < result.length; i++) {
      result[i] = ls.get(i);
    }

    return result;
  }

  /**
   * traverse the tree recursively
   *
   * @return all objects pre-order
   */
  @Override
  public E[] postOrder() {
    E[] result = (E[])new Object[size];
    List<E> ls = new LinkedList<>();
    postOrder(root, ls);

    for (int i = 0; i < result.length; i++) {
      result[i] = ls.get(i);
    }

    return result;
  }

  private int findHeight(Node<E> parent) {
    if (parent == null)
      return 0;

    int leftH = findHeight(parent.left);
    int rightH = findHeight(parent.right);
    if (parent == root)
      return Math.max(leftH, rightH);

    return Math.max(leftH, rightH) + 1;
  }

  /**
   * traverse through the tree and find out the tree height
   * @return height
   * @throws NullPointerException if the BST is empty
   */
  @Override
  public int findHeight() {
    return findHeight(root);
  }

  static final class Node<E> {

    E value;
    Node<E> left;
    Node<E> right;
    Node<E> parent;

    public Node(E value, Node<E> parent) {
      this.value = value;
      this.parent = parent;
    }

    public E getValue() {
      return value;
    }

    public void setValue(E value) {
      this.value = value;
    }

    public Node<E> getLeft() {
      return left;
    }

    public void setLeft(Node<E> left) {
      this.left = left;
    }

    public Node<E> getRight() {
      return right;
    }

    public void setRight(Node<E> right) {
      this.right = right;
    }

    public Node<E> getParent() {
      return parent;
    }

    public void setParent(Node<E> parent) {
      this.parent = parent;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof Node)) {
        return false;
      }
      Node<?> node = (Node<?>) o;
      return getValue().equals(node.getValue()) &&
          Objects.equals(getLeft(), node.getLeft()) &&
          Objects.equals(getRight(), node.getRight()) &&
          getParent().equals(node.getParent());
    }

    @Override
    public int hashCode() {
      return Objects.hash(getValue(), getLeft(), getRight(), getParent());
    }
  }
}
