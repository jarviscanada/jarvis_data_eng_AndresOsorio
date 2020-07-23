package ca.jrvs.practice.dataStructures.list;

import java.util.Arrays;
import java.util.List;

public class ArrayAPIs {

  public static void main(String[] args) {
    int[] intArray = new int[10];
    intArray[0] = 100;
    intArray[1] = 200;
    intArray[2] = 300;
    int[] intLineArray = {100, 200, 300};

    String[][] names = {
        {"hello", "this"},
        {"is", "a", "2D", "array"}
    };

    // copy array
    char[] source = {'a', 'b', 'c', 'd', 'f', 'g', 'h'};
    char[] dest = new char[4];
//    System.arraycopy(source, 1, dest, 0, 4);
//    System.out.println(dest);
    dest = Arrays.copyOf(source, 5);
    System.out.println(dest);
    System.out.println(new String(dest));

    // array to list
    List<String> fruits = Arrays.asList("apple", "orange");
    fruits = Arrays.asList(new String[]{"apple", "orange"});

    // copy
    String[] fruitArray = {"mango", "banana"};
    String[] anotherFruitArray = Arrays.copyOfRange(fruitArray, 0, 1);
    // an array variable points to the first element of the array in memory
    System.out.println(anotherFruitArray);

    // sort
    Arrays.sort(fruitArray);
    System.out.println(Arrays.toString(fruitArray));

    // binary search
    int exact = Arrays.binarySearch(fruitArray, "banana");
    System.out.println(exact);
  }
}
