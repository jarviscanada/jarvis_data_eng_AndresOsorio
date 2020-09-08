package ca.jrvs.apps.trading.model.domain;

import java.util.Objects;

public class Account implements Entity<Integer> {

  private Integer id;
  private Double amount;
  private Integer traderId;

  @Override
  public Integer getId() {
    return id;
  }

  @Override
  public void setId(Integer id) {
    this.id = id;
  }

  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public Integer getTraderId() {
    return traderId;
  }

  public void setTraderId(Integer traderId) {
    this.traderId = traderId;
  }

  @Override
  public String toString() {
    return "Account{" +
        "id=" + id +
        ", amount=" + amount +
        ", traderId=" + traderId +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Account)) {
      return false;
    }
    Account account = (Account) o;
    return Objects.equals(id, account.id) &&
        Objects.equals(amount, account.amount) &&
        Objects.equals(traderId, account.traderId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, amount, traderId);
  }
}
