package ca.jrvs.apps.practice;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.w3c.dom.ls.LSOutput;

class Main {

  public static void main(String args[]) {
    String[] strings = {"Hey", "how", "are", "you"};
    int[] numbers = {10, 15, 20, 25, 30};
    List<String> l = new ArrayList<>();

    IntStream is;
    DoubleStream ds;
    Stream<String> ss;
    Consumer<String> printer;

    LambdaStreamExc lse = new LambdaStreamExcImp();

//    is = lse.createIntStream(numbers);

//    ss = lse.filter(Arrays.stream(strings), "a");
//    ss.forEach(str -> System.out.println(str));

//    l = lse.toList(Arrays.stream(strings));
//    System.out.println(l);

//    l2 = lse.toList(Arrays.stream(numbers));
//    System.out.println(l2);

//    is = lse.createIntStream(2, 10);
//    is.forEach(System.out::println);

//    ds = lse.squareRootIntStream(Arrays.stream(numbers));
//    ds.forEach(d -> System.out.println(d));

//    is = lse.getOdd(Arrays.stream(numbers));
//    is.forEach(i -> System.out.println(i));

//    printer = lse.getLambdaPrinter("start>", "<end");
//    printer.accept("Different Message");

//    printer = lse.getLambdaPrinter("Message:", "!");
//    lse.printMessages(strings, printer);

//    lse.printOdd(lse.createIntStream(0, 25), lse.getLambdaPrinter("odd number:", "!"));

    List<Integer> ints1 = Arrays.asList(10, 15, 20, 25, 30);
    List<Integer> ints2 = Arrays.asList(35, 40, 45, 50, 55);
    // Unflattened stream of lists of ints
    Stream<List<Integer>> streamOfListsOfInts = Arrays.asList(ints1, ints2).stream();

    // Flattened stream of ints
    Stream<Integer> is2 = lse.flatNestedInt(streamOfListsOfInts);

    // Have to get again bc it was already used and closed on previous operation (line 59)
    streamOfListsOfInts = Arrays.asList(ints1, ints2).stream();
    // Prints as a stream made up of lists of ints
    streamOfListsOfInts.forEach(list -> System.out.print(list));
    System.out.println();
    // Prints as a single stream of ints
    is2.forEach(i -> System.out.println(i));
  }
}