package ca.jrvs.apps.practice;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public interface LambdaStreamExc {

  /**
   * Create a String stream from variable-length String arg; (0 or more strings in an array)
   *
   * @param strings
   * @return a stream of strings
   */
  Stream<String> createStrStream(String... strings);

  /**
   * Convert all strings to uppercase
   *
   * @param strings
   * @return a stream of strings in uppercase
   */
  Stream<String> toUpperCase(String... strings);

  /**
   * Filter strings that contain the pattern
   * e.g.:
   * filter(stringStream, "a") will return another stream with no strings containing "a"
   *
   * @param stringStream
   * @param pattern
   * @return a stream of strings filtered according to pattern
   */
  Stream<String> filter(Stream<String> stringStream, String pattern);

  /**
   * Create an IntStream from arr
   *
   * @param arr
   * @return an IntStream
   */
  IntStream createIntStream(int[] arr);

  /**
   * Convert a stream to list
   *
   * @param stream
   * @param <E>
   * @return the list resulting from stream
   */
  <E> List<E> toList(Stream<E> stream);

  /**
   * Convert an IntStream to a list
   *
   * @param intStream
   * @return list resulting from intStream
   */
  List<Integer> toList(IntStream intStream);

  /**
   * Create an IntStream range from start (inclusive) to end (exclusive)
   *
   * @param start
   * @param end
   * @return IntStream
   */
  IntStream createIntStream(int start, int end);

  /**
   * Convert intStream to a DoubleStream and compute the square root of each element
   *
   * @param intStream
   * @return DoubleStream containing the square root of each element in intStream
   */
  DoubleStream squareRootIntStream(IntStream intStream);

  /**
   * Filter all even numbers to return only odd numbers
   *
   * @param intStream
   * @return IntStream with only odd numbers from intStream
   */
  IntStream getOdd(IntStream intStream);

  /**
   * Return a lambda function that prints a message with a prefix and suffix;
   * This lambda can be useful to format logs
   *
   * You will learn:
   *    - functional interface http://bit.ly/2pTXRwM & http://bit.ly/33onFig
   *    - lambda syntax
   *
   * e.g.:
   * LambdaStreamExc lse = new LambdaStreamExcImp();
   * Consumer<String> printer = lse.getLambdaPrinter("start>", "<end");
   * printer.accept("Message body");
   *
   * sout:
   * start>Message body<end
   *
   * @param prefix
   * @param suffix
   * @return Object that implements the Consumer<T> functional interface
   *          and its accept(T) method
   */
  Consumer<String> getLambdaPrinter(String prefix, String suffix);

  /**
   * Print each message with a given printer
   * Use getLambdaPrinter method
   * eg.:
   * String[] messages = {"a", "b", "c"};
   * lse.printMessages(messages, lse.getLambdaPrinter("msg:". "!"));
   *
   * sout:
   * msg:a!
   * msg:b!
   * msg:c!
   *
   * @param messages messages to printed
   * @param printer lambda function/expression to print messages a certain way
   */
  void printMessages(String[] messages, Consumer<String> printer);

  /**
   * Print all odd number from a intStream.
   * Please use `createIntStream` and `getLambdaPrinter` methods
   *
   * e.g.
   * lse.printOdd(lse.createIntStream(0, 5), lse.getLambdaPrinter("odd number:", "!"));
   *
   * sout:
   * odd number:1!
   * odd number:3!
   * odd number:5!
   *
   * @param intStream
   * @param printer
   */
  void printOdd(IntStream intStream, Consumer<String> printer);

  /**
   * Square each number from the input.
   * Please write two solutions and compare difference
   *   - using flatMap
   *
   * @param ints
   * @return
   */
  Stream<Integer> flatNestedInt(Stream<List<Integer>> ints);

}