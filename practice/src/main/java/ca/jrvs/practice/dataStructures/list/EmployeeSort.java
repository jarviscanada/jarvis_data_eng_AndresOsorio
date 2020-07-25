package ca.jrvs.practice.dataStructures.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class EmployeeSort {

  public static void main(String[] args) {
    List<Employee> employees = new ArrayList<>();
    employees.add(new Employee(1, "Jo", 25, 50000));
    employees.add(new Employee(2, "Martha", 20, 60000));
    employees.add(new Employee(3, "Valen", 18, 45000));
    employees.add(new Employee(4, "Bob", 30, 80000));
    employees.add(new Employee(5, "Alan", 23, 75000));

    // uses Employee.compareTo method (natural ordering)
    Collections.sort(employees);
    System.out.println(employees);

    AgeComparator ac = new AgeComparator();
    Collections.sort(employees, ac);
    System.out.println(employees);

    SalaryComparator sc = new SalaryComparator();
    Collections.sort(employees, sc);
    System.out.println(employees);

    // using lambda
    Comparator<Employee> lambdaAc = (Employee e1, Employee e2) -> e1.getAge() - e2.getAge();
    Collections.sort(employees, lambdaAc);
    System.out.println(employees);

    Comparator<Employee> lambdaSc = (Employee e1, Employee e2) -> (int)(e1.getSalary() - e2.getSalary());
    Collections.sort(employees, lambdaSc);
    System.out.println(employees);
  }
}
