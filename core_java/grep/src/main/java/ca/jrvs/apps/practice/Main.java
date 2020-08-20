package ca.jrvs.apps.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Main {

  public static void main(String args[]) {
    String[] strings = {"Hey", "how", "are", "you"};
    int[] numbers = {10, 15, 20, 25, 30};
    List<String> list = new ArrayList<>();
    List<Integer> otherList;

    IntStream is;
    DoubleStream ds;
    Stream<String> ss;
    Consumer<String> printer;

    LambdaStreamExc lse = new LambdaStreamExcImp();

    is = lse.createIntStream(numbers);

    ss = lse.filter(Arrays.stream(strings), "a");
    ss.forEach(str -> System.out.println(str));

    list = lse.toList(Arrays.stream(strings));
    System.out.println(list);

    otherList = lse.toList(Arrays.stream(numbers));
    System.out.println(otherList);

    is = lse.createIntStream(2, 10);
    is.forEach(System.out::println);

    ds = lse.squareRootIntStream(Arrays.stream(numbers));
    ds.forEach(d -> System.out.println(d));

    is = lse.getOdd(Arrays.stream(numbers));
    is.forEach(i -> System.out.println(i));

    printer = lse.getLambdaPrinter("start>", "<end");
    printer.accept("Different Message");

    printer = lse.getLambdaPrinter("Message:", "!");
    lse.printMessages(strings, printer);

    lse.printOdd(lse.createIntStream(0, 25), lse.getLambdaPrinter("odd number:", "!"));
  }
}