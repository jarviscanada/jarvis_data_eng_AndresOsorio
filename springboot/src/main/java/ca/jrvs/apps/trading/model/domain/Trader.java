package ca.jrvs.apps.trading.model.domain;

import java.sql.Date;
import java.util.Objects;

public class Trader implements Entity<Integer> {

  private Integer id;
  private String firstName;
  private String lastName;
  private String email;
  private Date dob;
  private String country;

  @Override
  public Integer getId() {
    return id;
  }

  @Override
  public void setId(Integer id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Date getDob() {
    return dob;
  }

  public void setDob(Date dob) {
    this.dob = dob;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  @Override
  public String toString() {
    return "Trader{" +
        "id=" + id +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", email='" + email + '\'' +
        ", dob=" + dob +
        ", country='" + country + '\'' +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Trader)) {
      return false;
    }
    Trader trader = (Trader) o;
    return Objects.equals(id, trader.id) &&
        Objects.equals(firstName, trader.firstName) &&
        Objects.equals(lastName, trader.lastName) &&
        Objects.equals(email, trader.email) &&
        Objects.equals(dob, trader.dob) &&
        Objects.equals(country, trader.country);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, lastName, email, dob, country);
  }
}
