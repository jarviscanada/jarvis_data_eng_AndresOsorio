package ca.jrvs.apps.trading.model.domain;

import java.util.Objects;

public class SecurityOrder implements Entity<Integer> {

  private Integer id;
  private Integer accountId;
  private Double price;
  private Integer size;
  private String ticker;
  private String notes;
  public enum Status {
    FILLED, CANCELLED, PENDING
  };
  private String status;

  @Override
  public Integer getId() {
    return id;
  }

  @Override
  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getAccountId() {
    return accountId;
  }

  public void setAccountId(Integer accountId) {
    this.accountId = accountId;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public Integer getSize() {
    return size;
  }

  public void setSize(Integer size) {
    this.size = size;
  }

  public String getTicker() {
    return ticker;
  }

  public void setTicker(String ticker) {
    this.ticker = ticker;
  }

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return "SecurityOrder{" +
        "id=" + id +
        ", accountId=" + accountId +
        ", price=" + price +
        ", size=" + size +
        ", ticker='" + ticker + '\'' +
        ", notes='" + notes + '\'' +
        ", status='" + status + '\'' +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof SecurityOrder)) {
      return false;
    }
    SecurityOrder that = (SecurityOrder) o;
    return Objects.equals(id, that.id) &&
        Objects.equals(accountId, that.accountId) &&
        Objects.equals(price, that.price) &&
        Objects.equals(size, that.size) &&
        Objects.equals(ticker, that.ticker) &&
        Objects.equals(notes, that.notes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, accountId, price, size, ticker, notes);
  }
}
