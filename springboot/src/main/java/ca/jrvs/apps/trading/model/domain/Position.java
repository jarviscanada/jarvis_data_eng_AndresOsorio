package ca.jrvs.apps.trading.model.domain;

import java.util.Objects;

public class Position implements Entity<Integer> {

  private Integer position;
  private Integer accountId;
  private String ticker;

  @Override
  public Integer getId() {
    return accountId;
  }

  @Override
  public void setId(Integer accountId) {
    this.accountId = accountId;
  }

  public Integer getAccountId() {
    return accountId;
  }

  public void setAccountId(Integer accountId) {
    this.accountId = accountId;
  }

  public String getTicker() {
    return ticker;
  }

  public void setTicker(String ticker) {
    this.ticker = ticker;
  }

  @Override
  public String toString() {
    return "Position{" +
        "position=" + position +
        ", accountId=" + accountId +
        ", ticker='" + ticker + '\'' +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Position)) {
      return false;
    }
    Position position1 = (Position) o;
    return Objects.equals(position, position1.position) &&
        Objects.equals(accountId, position1.accountId) &&
        Objects.equals(ticker, position1.ticker);
  }

  @Override
  public int hashCode() {
    return Objects.hash(position, accountId, ticker);
  }
}
