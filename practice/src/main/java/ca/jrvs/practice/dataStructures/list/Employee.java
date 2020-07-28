package ca.jrvs.practice.dataStructures.list;

import java.util.Objects;

public class Employee implements Comparable<Employee> {

  private int id;
  private String name;
  private int age;
  private long salary;

  public Employee() {
  }

  @Override
  public String toString() {
    return "{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", age=" + age +
        ", salary=" + salary +
        "}\n";
  }

  public Employee(int id, String name, int age, long salary) {
    this.id = id;
    this.name = name;
    this.age = age;
    this.salary = salary;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public long getSalary() {
    return salary;
  }

  public void setSalary(long salary) {
    this.salary = salary;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Employee)) {
      return false;
    }
    Employee employee = (Employee) o;
    return id == employee.id &&
        age == employee.age &&
        salary == employee.salary &&
        name.equals(employee.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, age, salary);
  }

  @Override
  public int compareTo(Employee o) {
    return this.age - o.getAge();
  }
}
