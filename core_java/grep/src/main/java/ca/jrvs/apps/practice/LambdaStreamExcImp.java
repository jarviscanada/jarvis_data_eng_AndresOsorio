package ca.jrvs.apps.practice;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LambdaStreamExcImp implements LambdaStreamExc {

  @Override
  public Stream<String> createStrStream(String... strings) {
    return Arrays.stream(strings);
  }

  @Override
  public Stream<String> toUpperCase(String... strings) {
    return Arrays.stream(strings).map(str -> str.toUpperCase());
  }

  @Override
  public Stream<String> filter(Stream<String> stringStream, String pattern) {
    return stringStream.filter(str -> !str.contains(pattern));
  }

  @Override
  public IntStream createIntStream(int[] arr) {
    return Arrays.stream(arr);
  }

  @Override
  public <E> List<E> toList(Stream<E> stream) {
    List<E> l = new ArrayList<>();
    stream.forEach(e -> l.add(e));
    return l;
  }

  @Override
  public List<Integer> toList(IntStream intStream) {
    List<Integer> l = new ArrayList<>();
    intStream.forEach(i -> l.add(i));
    return l;
  }

  @Override
  public IntStream createIntStream(int start, int end) {
    return IntStream.range(start, end);
  }

  @Override
  public DoubleStream squareRootIntStream(IntStream intStream) {
    DoubleStream ds = intStream.asDoubleStream().map(d -> Math.sqrt(d));
    return ds;
  }

  @Override
  public IntStream getOdd(IntStream intStream) {
    return intStream.filter(i -> (i % 2 != 0));
  }

  @Override
  public Consumer<String> getLambdaPrinter(String prefix, String suffix) {

// A lambda function is the implementation of the only method in a functional interface;
// Consumer<T> is a functional interface and by implementing its accept() only method we
// create out function

//    Consumer<String> c = new Consumer<String>() {
//      @Override
//      public void accept(String message) {
//        System.out.println(prefix + message + suffix);
//      }
//    };

    // Same as above in short-hand notation
    Consumer<String> c = message -> System.out.println(prefix + message + suffix);
    return c;
  }

  @Override
  public void printMessages(String[] messages, Consumer<String> printer) {
    Arrays.stream(messages).forEach(message -> printer.accept(message));
  }

  @Override
  public void printOdd(IntStream intStream, Consumer<String> printer) {
    getOdd(intStream).forEach(i -> printer.accept(Integer.toString(i)));
  }

  @Override
  public Stream<Integer> flatNestedInt(Stream<List<Integer>> ints) {
//    List<Integer> tmp = new ArrayList<>();
//    ints.forEach(l -> l.stream().forEach(i -> tmp.add(i)));
//    Stream<Integer> is = tmp.stream();

    // same as above but simplified with flatMap
    // flatMap flattens nested collections/streams easily
    Stream<Integer> is = ints.flatMap(list -> list.stream());
    return is;
  }
}