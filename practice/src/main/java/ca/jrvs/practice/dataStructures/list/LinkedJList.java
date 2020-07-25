package ca.jrvs.practice.dataStructures.list;

public class LinkedJList<E> implements JList<E> {

  private static class Node<E> {
    E element;
    Node<E> next;
    Node<E> prev;

    Node(E element, Node<E> prev, Node<E> next) {
      this.element = element;
      this.prev = prev;
      this.next = next;
    }
  }

  /**
   * Pointer to first node.
   * Invariant: (first == null && last == null) ||
   *            (first.prev == null && first.item != null)
   */
  private Node<E> head;

  /**
   * Pointer to last node.
   * Invariant: (first == null && last == null) ||
   *            (last.next == null && last.item != null)
   */
  private Node<E> tail;

  private int size;

  /**
   * Construct empty LinkedJList
   */
  public LinkedJList() {
  }

  private void inRange(int i) {
    if (i < 0 || i > size)
      throw new IllegalArgumentException("Out of range");
  }

  private void notNull(Object o) {
    if (o == null)
      throw new NullPointerException("Element is null");
  }

  @Override
  public boolean add(E e) {
    notNull(e);
    size++;
    if (head == null) {
      head = new Node<>(e, null, null);
      tail = head;
    } else {
      Node<E> newTail = new Node<>(e, tail, null);
      if (size == 2) {
        tail = newTail;
        head.next = tail;
        return true;
      }

      tail.next = newTail;
      tail = newTail;
    }
    return true;
  }

  @Override
  public Object[] toArray() {
    if (isEmpty())
      return new Object[0];

    Object[] result = new Object[size];
    Node<E> current = head;
    int i = 0;
    while (current != null) {
      result[i] = current.element;
      current = current.next;
      i++;
    }
    return result;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public boolean isEmpty() {
    return (size == 0);
  }

  @Override
  public int indexOf(Object o) {
    notNull(o);
    Node<E> current = head;
    int i = 0;
    while (current != null) {
      if (current.element.equals(o))
        return i;

      current = current.next;
      i++;
    }
    return -1;
  }

  @Override
  public boolean contains(Object o) {
    return (indexOf(o) >= 0);
  }

  @Override
  public E get(int index) {
    inRange(index);

    Node<E> current = head;
    int i = 0;
    while (current != null) {
      if (i == index)
        break;

      current = current.next;
      i++;
    }
    return current.element;
  }

  @Override
  public E remove(int index) {
    inRange(index);

    E result;
    size--;
    if (size == 0) {
      result = head.element;
      head = tail = null;
    } else if (index == 0) {
      result = head.element;
      head = head.next;
    } else if (index == (size + 1)) {
      result = tail.element;
      tail = tail.prev;
    } else {
      result = get(index);
      int i = 0;
      Node<E> current = head;
      while (current != null) {
        if (i == index) {
          current.prev.next = current.next;
          current.next.prev = current.prev;
          return result;
        }
        current = current.next;
        i++;
      }
    }

    return result;
  }

  @Override
  public void clear() {
    head = tail = null;
    size = 0;
  }
}
