package ca.jrvs.practice.dataStructures.stackQueue;

import ca.jrvs.practice.dataStructures.list.JList;
import ca.jrvs.practice.dataStructures.list.LinkedJList;

public class LinkedJListJDeque<E> implements JDeque<E> {

  private JList<E> data = new LinkedJList<>();

  @Override
  public boolean add(E e) {
    return data.add(e);
  }

  @Override
  public E remove() {
    return data.removeFirst();
  }

  @Override
  public E pop() {
    return data.removeFirst();
  }

  @Override
  public void push(E e) {
    data.addFirst(e);
  }

  @Override
  public E peek() {
    return data.get(0);
  }
}
